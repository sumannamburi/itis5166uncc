package nbadDB;


public class DefaultUsers {

    public static void setDefaultUsers() {

        String userName = "alicejones";
        String firstName = "Alice";
        String lastName = "Jones";
        String password = "alice";
        AdminUsers.addUser(userName, firstName, lastName, password);

        userName = "bobsmith";
        firstName = "Bob";
        lastName = "Smith";
        password = "bob";
        AdminUsers.addUser(userName, firstName, lastName, password);

        userName = "carlbrown";
        firstName = "Carl";
        lastName = "Brown";
        password = "carl";
        AdminUsers.addUser(userName, firstName, lastName, password);

        userName = "debbiejames";
        firstName = "Debbie";
        lastName = "James";
        password = "debbie";
        AdminUsers.addUser(userName, firstName, lastName, password);
    }

}
