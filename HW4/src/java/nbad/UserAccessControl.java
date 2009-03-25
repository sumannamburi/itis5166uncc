package nbad;

import nbadDB.*;

public class UserAccessControl {

    // validation logic

    public static boolean isValidLogin (String userName, String password) {

        // is the user on file?

        if (AdminUsers.getPassword(userName) == null) {
            return false;
        }
        // does the password match passeod from table?

        String passwordFromDB = AdminUsers.getPassword(userName);
        if (password.equals(passwordFromDB)) {
            return true;
        }

        return false;
    }

    public static boolean isUserNameInUse (String userName) {

        if (AdminUsers.getUser(userName) == null) {
            return false;
        }

        return true;
    }


    public static void setUser (String userName, String password, String firstName, String lastName) {

        AdminUsers.addUser(userName, firstName, lastName, password);

    }

    public static User getUser(String userName) {

        return AdminUsers.getUser(userName);

    }
}