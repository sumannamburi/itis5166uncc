package nbadDB;

import nbad.*;
import java.sql.*;
import java.util.*;

public class AdminHoldings {

    // public methods

    protected static void createHoldingsTable() {

        Statement statement = Admin.getNewStatement();
        try {
            statement.execute("create table HOLDINGS(" +
                              "userName varchar(40) not null, " +
                              "stockName varchar(40) not null, " +
                              "quantity int not null, " +
                              "PRIMARY KEY (userName, stockName))");
            LogDB.setMessage("Created a new table: " + "HOLDINGS");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState()))  {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                LogDB.setMessage("ERROR: Could not create HOLDINGS table: " + se);
            }
        }
    }

    public static Holding addHolding(String userName, String stockName, Integer quantity) {

        Connection connection = Admin.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
        insertRow = connection.prepareStatement("insert into HOLDINGS values (?, ?, ?)");
        insertRow.setString(1, userName);
        insertRow.setString(2, stockName);
        insertRow.setInt(3, quantity);
        insertRow.executeUpdate();
        }catch (SQLException se) {
            if (( (se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState()) ))) {
                LogDB.setMessage("ERROR: Could not insert record into HOLDINGS; dup primary key: " + userName + " " + stockName);
            } else {
                LogDB.setMessage("ERROR: Could not insert record into HOLDINGS: " + userName + " " + stockName + " " + quantity + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not add row to HOLDINGS table: " + userName + " " + stockName + " " + quantity);
            return null;
        }
        LogDB.setMessage("Added holding to HOLDINGS table: " + userName + " " + stockName + " " + quantity);

        // return the new Holdings object
        return new Holding(userName, stockName, quantity);
    }

    public static Holding getHolding(String userName, String stockName) {

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        Integer quantity = 0;

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, stockName, quantity FROM HOLDINGS WHERE userName ='" + userName + "' AND stockName ='" + stockName + "' ");
            if (!resultSet.next()) {
                LogDB.setMessage("WARNING: Could not find holding in HOLDINGS table: " + userName + " " + stockName);
                return null;
            } else {
                quantity = resultSet.getInt("quantity");
                LogDB.setMessage("Found holding in HOLDINGS table: " + userName + " " + stockName);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + "SELECT userName, stockName, quantity FROM HOLDINGS WHERE userName ='" + userName + "' AND stockName ='" + stockName + "' ");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return new Holding(userName, stockName, quantity);
    }

    public static ArrayList<Holding> getAllHoldings() {
        ArrayList<Holding> holdings = new ArrayList<Holding>();

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String userName = "";
        String stockName = "";
        Integer quantity = 0;

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, stockName, quantity FROM HOLDINGS");
            while (resultSet.next()) {
                userName = resultSet.getString("userName");
                stockName = resultSet.getString("stockName");
                quantity = resultSet.getInt("quantity");
                Holding holding = new Holding(userName, stockName, quantity);
                holdings.add(holding);
                LogDB.setMessage("Found holding in HOLDINGS table: " + userName + " " + stockName + " " + quantity);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement in: " + "AdminHoldings.getAllHoldings()");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return holdings;
    }

    public static Holding updateHolding(String userName, String stockName, Integer quantity) {

        Connection connection = Admin.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
        insertRow = connection.prepareStatement("update HOLDINGS set quantity =? where userName =? and stockName=?");
        insertRow.setInt(1, quantity);
        insertRow.setString(2, userName);
        insertRow.setString(3, stockName);
        insertRow.executeUpdate();
        }catch (SQLException se) {
            if (( (se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState()) ))) {
                LogDB.setMessage("ERROR: Could not insert record into HOLDINGS; dup primary key: " + userName + " " + stockName);
            } else {
                LogDB.setMessage("ERROR: Could not insert record into HOLDINGS: " + userName + " " + stockName + " " + quantity + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not add row to HOLDINGS table: " + userName + " " + stockName + " " + quantity);
            return null;
        }
        LogDB.setMessage("Added holding to HOLDINGS table: " + userName + " " + stockName + " " + quantity);

        // return the new Holdings object
        return new Holding(userName, stockName, quantity);
    }
}

