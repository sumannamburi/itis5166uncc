package nbad;

public class User {

    private String userName;
    private String lastName;
    private String firstName;

    public User(String userName, String lastName, String firstName) {
        this.userName = userName;
    	this.lastName = lastName;
    	this.firstName = firstName;
    }

    // getters

    public String getUserName () {
        return userName;
    }

    public String getLastName () {
        return lastName;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getFullName () {
        return firstName + " " + lastName;
    }
}
