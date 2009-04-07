/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

/**
 *
 * @author owner
 */
public class TransactionBean {

    private String stockname;
    private String action;
    private double price;
    private double total;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getAction() {
        return action;
    }

    public String getStockname() {
        return stockname;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public TransactionBean(String name, String action, double price, double total, int qty)
    {
        this.stockname=name;
        this.action=action;
        this.price=price;
        this.total=total;
        this.quantity=qty;
    }



}
