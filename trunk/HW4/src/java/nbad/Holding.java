package nbad;

public class Holding {

    private String userName;
    private String stockName;
    private Integer quantity;

    public Holding(String userName, String stockName, Integer quantity) {
        this.userName = userName;
    	this.stockName = stockName;
    	this.quantity = quantity;
    }

    // getters

    public String getUserName () {
        return userName;
    }

    public String getStockName () {
        return stockName;
    }

    public Integer getQuantity () {
        return quantity;
    }
}