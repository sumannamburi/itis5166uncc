package nbadDB;

import java.net.*;
import java.sql.*;
import java.util.*;

public class Admin {
    private static String jdbc = "jdbc:derby:";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "NBAD_DATABASE";
    private static Connection connection;

    public Admin() {
        // Make the data base connection to nbadDB

        // Need to find path to the current class in order to set the path for the DB
        Class thisClass = getClass();
        // setup the protocal paramenter
        String protocol = jdbc + getPathForDB(thisClass);
        // load the desired JDBC driver
        loadDriver();
        // initialize connection properties
        Properties props = new Properties();

        // try to build the DB connection
        try {
        /*
         * This connection specifies create=true in the connection URL to
         * cause the database to be created when connecting for the first
         * time. To remove the database, remove the directory derbyDB (the
         * same as the database name) and its contents.
         */
        connection = DriverManager.getConnection(protocol + dbName + ";create=true", props);
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not create connection: " + protocol + dbName + ";create=true" + props);
            LogDB.setMessage("ERROR: Could not create connection: " + e);
            connection = null;
        }

        // create the users table
        AdminUsers.createUsersTable();
        // add defalut users
        DefaultUsers.setDefaultUsers();

        // create the categories table
        AdminCategories.createCategoriesTable();
        // add defalut categories
        DefaultCategories.setDefaultCategories();

        // create the stocks table
        AdminStocks.createStocksTable();
        // add defalut stocks
        DefaultStocks.setDefaultStocks();

        // create the holdings table
        AdminHoldings.createHoldingsTable();
        // add defalut holdings
        DefaultHoldings.setDefaultHoldings();
    }
    
    public static Statement getNewStatement() {
        Statement statement;
        try {
        /* Creating a statement object that we can use for running
         * SQL statements commands against the database.*/
        statement = connection.createStatement();
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not create SQL statement object: " + e);
            statement = null;
        }
        return statement;
    }

    public static Connection getConnection() {
        
        return connection;
    }

    public static void shutDownDB() {
        try {
            // the shutdown=true attribute shuts down Derby
            DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException se) {
            if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()) ))) {
                // we got the expected exception
                LogDB.setMessage("Shut down data base: " + "jdbc:derby:;shutdown=true");
            } else {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                LogDB.setMessage("ERROR: Could not shut down data base: " + se);
            }

        }
    }

    private static String getPathForDB(Class thisClass) {
        URL url = thisClass.getResource("");
        // Must obtain a string of tht path by first converting the url to an uri
        URI uri;
        try {
            uri = new URI(url.toString());
        }
        catch (Exception e) {
            LogDB.setMessage("ERROR: Unable find path for DB location: " + e);
            return null;
        }
        // remove first element in uri string
        String path = uri.getPath().substring(1);
        // split the path so that the DB will be placed outside the 'build' folder, so that NetBeans will not 'Clean' it
        String[] pathSplitAtBuild_html = path.split("build");

        LogDB.setMessage("Path for DB set to: " + pathSplitAtBuild_html[0]);
        return pathSplitAtBuild_html[0];
    }

    private void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running.
         *
         *  In an embedded environment, any static Derby system properties
         *  must be set before loading the driver to take effect.
         */
        try {
            Class.forName(driver).newInstance();
            LogDB.setMessage("Loaded the appropriate driver: " + driver);
        } catch (ClassNotFoundException cnfe) {
            LogDB.setMessage("ERROR: Unable to load the JDBC driver: " + driver);
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            LogDB.setMessage("ERROR: Unable to instantiate the JDBC driver: " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            LogDB.setMessage("ERROR: Not allowed to access the JDBC driver: " + driver);
            iae.printStackTrace(System.err);
        }
    }
}
