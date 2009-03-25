package nbadDB;


public class DefaultCategories {

    public static void setDefaultCategories() {

        String name = "Energy";
        AdminCategories.addCategory(name);

        name = "Healthcare";
        AdminCategories.addCategory(name);

        name = "Transportation";
        AdminCategories.addCategory(name);

    }

}