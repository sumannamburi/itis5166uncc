package nbadDB;

import nbad.*;
import java.sql.*;
import java.util.*;

public class AdminUsers {

    // public methods

    protected static void createUsersTable() {

        Statement statement = Admin.getNewStatement();
        try {
            statement.execute("create table USERS(" +
                              "userName  varchar(40) not null, " +
                              "firstName  varchar(40) not null, " +
                              "lastName  varchar(40) not null, " +
                              "password varchar(40) not null," +
                              "PRIMARY KEY (userName))");
            LogDB.setMessage("Created a new table: " + "USERS");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState()))  {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                LogDB.setMessage("ERROR: Could not create USERS table: " + se);
            }
        }
    }

    public static User addUser(String userName, String firstName, String lastName, String password) {

        Connection connection = Admin.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
        insertRow = connection.prepareStatement("insert into users values (?, ?, ?, ?)");
        insertRow.setString(1, userName);
        insertRow.setString(2, firstName);
        insertRow.setString(3, lastName);
        insertRow.setString(4, password);
        insertRow.executeUpdate();

        }catch (SQLException se) {
            if (( (se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState()) ))) {
                LogDB.setMessage("ERROR: Could not insert record into USERS; dup primary key: " + userName);
            } else {
                LogDB.setMessage("ERROR: Could not add row to USERS table: " + userName + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not add row to USERS table: " + userName);
            return null;
        }
        LogDB.setMessage("Added user to USERS table: " + userName);

        // return the new User object
        return new User(userName, lastName, firstName);
    }

    public static User getUser(String userName) {

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String firstName = "";
        String lastName = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, firstName, lastName, password FROM users WHERE userName ='" + userName + "' ORDER BY userName");
            if (!resultSet.next()) {
                LogDB.setMessage("WARNING: Could not find user in USERS table: " + userName);
                return null;
            } else {
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                LogDB.setMessage("Found user in users table: " + userName);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + "SELECT userName, firstName, lastName, password FROM users WHERE userName ='" + userName + "' ORDER BY userName");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return new User(userName, lastName, firstName);
    }

    public static String getPassword(String userName) {
        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String password = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, firstName, lastName, password FROM users WHERE userName ='" + userName + "' ORDER BY userName");
            if (!resultSet.next()) {
                LogDB.setMessage("WARNIMNG: Could not find user in USERS table: " + userName);
                return null;
            } else {
                password = resultSet.getString("password");
                LogDB.setMessage("Found user in users table: " + userName);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + "SELECT userName, firstName, lastName, password FROM users WHERE userName ='" + userName + "' ORDER BY userName");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return password;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        
        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String userName = "";
        String firstName = "";
        String lastName = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, firstName, lastName, password FROM users ORDER BY userName");
            while (resultSet.next()) {
                userName = resultSet.getString("userName");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                User user = new User(userName, lastName, firstName);
                users.add(user);
                LogDB.setMessage("Found user in USERS table: " + userName);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement in: " + "AdminUsers.getAllUsers()");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return users;
    }
}

