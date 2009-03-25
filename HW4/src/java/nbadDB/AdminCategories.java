package nbadDB;

import nbad.*;
import java.sql.*;
import java.util.*;

public class AdminCategories {

    // public methods

    protected static void createCategoriesTable() {

        Statement statement = Admin.getNewStatement();
        try {
            statement.execute("create table CATEGORIES(" +
                              "name  varchar(40) not null, " +
                              "PRIMARY KEY (name))");
            LogDB.setMessage("Created a new table: " + "CATEGORIES");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState()))  {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                LogDB.setMessage("ERROR: Could not create CATEGORIES table: " + se);
            }
        }
    }

    public static Category addCategory(String name) {

        Connection connection = Admin.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
        insertRow = connection.prepareStatement("insert into CATEGORIES values (?)");
        insertRow.setString(1, name);
        insertRow.executeUpdate();
        } catch (SQLException se) {
            if (( (se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState()) ))) {
                LogDB.setMessage("ERROR: Could not insert record into CATEGORIES; dup primary key: " + name);
            } else {
                LogDB.setMessage("ERROR: Could not add row to CATEGORIES table: " + name + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            LogDB.setMessage("ERROR: Could not add row to CATEGORIES table: " + name);
            return null;
        }
        LogDB.setMessage("Added name to CATEGORIES table: " + name);

        // return the new Category object
        return new Category(name);
    }

    public static Category getCategory(String name) {

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String categoryName = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT name FROM CATEGORIES WHERE name ='" + name + "' ORDER BY name");
            if (!resultSet.next()) {
                LogDB.setMessage("WARNING: Could not find user in users table: " + name);
                return null;
            } else {
                categoryName = resultSet.getString("name");
                LogDB.setMessage("Found category in CATEGORIES table: " + name);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + "SELECT name FROM CATEGORIES WHERE name ='" + name + "' ORDER BY name");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return new Category(categoryName);
    }

    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();

        Statement statement = Admin.getNewStatement();
        ResultSet resultSet = null;

        String name = "";

        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT name FROM CATEGORIES ORDER BY name");
            while (resultSet.next()) {
                name = resultSet.getString("name");
                Category category = new Category(name);
                categories.add(category);
                LogDB.setMessage("Found category in CATEGORIES table: " + name);
            }
        } catch (SQLException se) {
            LogDB.setMessage("ERROR: Could not exicute SQL statement in: " + "AdminCategories.getAllCategories()");
            LogDB.setMessage("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return categories;
    }
}

