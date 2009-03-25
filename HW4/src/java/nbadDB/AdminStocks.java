package nbadDB;

import nbad.*;
import java.sql.*;
import java.util.*;

public class AdminStocks {

    // public methods

    protected static void createStocksTable() {

        Statement statement = Admin.getNewStatement();
        try {
            statement.execute("create table STOCKS(" +
                              "name varchar(40) not null, " +
                              "price double not null, " +
                              "category varchar(40) not null, " +
                              "PRIMARY KEY (name))");
            LogDB.setMessage("Created a new table: " + "users");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState()))  {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                LogDB.setMessage("ERROR: Could not create STOCKS table: " + se);
            }
        }
    }

    public static Stock addStock(String name, Double price, String category) {

        Connection connection = Admin.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
        insertRow = connection.prepareStatement("insert into STOCKS values (?, ?, ?)");
        insertRow.setString(1, name);
        insertRow.setDouble(2, price);
        insertRow.setString(3, category);
        insertRow.executeUpdate();
        } catch (SQLException se) {
            if (( (se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState()) ))) {
                LogDB.setMessage("ERROR: Could not insert record into STOCKS; dup primary key: " + name);
            } else {
                LogDB.setMessage("ERROR: Could not add row to STOCKS table: " + name + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not add row to STOCKS table: " + name);
            return null;
        }
        LogDB.setMessage("Added stock to STOCKS table: " + name);

        // return the new Stock object
        return new Stock(name, price, category);
    }

    public static Stock getStock(String name) {

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        Double price = 0.00;
        String category = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT name, price, category FROM STOCKS WHERE name ='" + name + "' ORDER BY name");
            if (!resultSet.next()) {
                LogDB.setMessage("WARNING: Could not find stock in STOCKS table: " + name);
                return null;
            } else {
                price = resultSet.getDouble("price");
                category = resultSet.getString("category");
                LogDB.setMessage("Found stock in STOCKS table: " + name);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + "SELECT name, price, category FROM STOCKS WHERE name ='" + name + "' ORDER BY name");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return new Stock(name, price, category);
    }

    public static ArrayList<Stock> getAllStocks() {
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String stockName = "";
        Double price = 0.00;
        String category = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT name, price, category FROM STOCKS ORDER BY name");
            while (resultSet.next()) {
                stockName = resultSet.getString("name");
                price = resultSet.getDouble("price");
                category = resultSet.getString("category");
                Stock stock = new Stock(stockName, price, category);
                stocks.add(stock);
                LogDB.setMessage("Found stock in stocks table: " + stockName);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement in: " + "AdminStocks.getAllStocks()");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return stocks;
    }
}

