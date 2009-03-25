package nbad;

public class Stock {

    private String name;
    private Double price;
    private String category;

    public Stock(String name, Double price, String category) {
        this.name = name;
    	this.price = price;
    	this.category = category;
    }

    // getters

    public String getName () {
        return name;
    }

    public Double getPrice () {
        return price;
    }

    public String getCategory () {
        return category;
    }
}