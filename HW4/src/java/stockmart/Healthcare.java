/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servletUtil.ServletUtilities;
import nbad.*;
import nbadDB.*;

/**
 *
 * @author Ebonie Williams
 */
public class Healthcare extends HttpServlet {

    private String [] healthStocks = {DefaultStocks.ABCP,DefaultStocks.PMED};
    private HttpServletRequest requestStock;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        //VERIFY SESSION
        if(Session.hasLoggedIn(request)!= true)
            response.sendRedirect(ServletUtilities.PATHROOT+"/Login");
        else{
        String userFullName = Session.userFullName(request);
        requestStock = request;

        /**
         *  following code references TA's HW5 hint
         */
        // start by getting the stock list
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        for(int e=0; e<healthStocks.length; e++)
        {
            stocks.add(AdminStocks.getStock(healthStocks[e]));// AdminStocks.getAllStocks();
        }

        // create a HashMap to store the number of shares owned of each stock by our user

        HashMap<String, Integer> numberOfSharesOwned = new HashMap<String, Integer>();

        // work through the stocks list and see how many of each stock is owned by our user

        Iterator stockEntries = stocks.iterator();

        while (stockEntries.hasNext()) {

            Stock stock = (Stock)stockEntries.next();
            String stockName = stock.getName();
            numberOfSharesOwned.put(stockName, getSharesOwned(stockName));

        }

        HashMap<String, Double> pricePerOwned = new HashMap<String, Double>();
        stockEntries = stocks.iterator();
        while (stockEntries.hasNext()) {
            Stock stock = (Stock)stockEntries.next();
            String stockName = stock.getName();
            pricePerOwned.put(stockName, getSharePrice(stockName));
        }

        HashMap<String, Double> totalPerStock = new HashMap<String, Double>();
        stockEntries = stocks.iterator();
        while (stockEntries.hasNext()) {
            Stock stock = (Stock)stockEntries.next();
            String stockName = stock.getName();
            totalPerStock.put(stockName, getTotal(stockName));
        }

        // save the user name, stocks list, and the number of shares table as request attibutes
        request.setAttribute("stocks", stocks);
        request.setAttribute("numberOfSharesOwned", numberOfSharesOwned);
        request.setAttribute("pricePerOwned", pricePerOwned);
        request.setAttribute("totalPerStock", totalPerStock);


        // send the user to the page
        RequestDispatcher dispatcher = request.getRequestDispatcher("Healthcare.jsp");
        dispatcher.forward(request, response);

        }
    }

    private double getSharePrice(String stockname) {
        Stock stock = AdminStocks.getStock(stockname);
        if(stock!=null)
            return stock.getPrice();
        else
            return 0.00;
    }

    private int getSharesOwned(String stockname) throws ServletException {
        Holding hold = AdminHoldings.getHolding(getUserName(requestStock), stockname);
        if(hold!=null)
            return hold.getQuantity();
        else
            return 0;
    }

    private double getTotal(String stockname) throws ServletException {
        double price = getSharePrice(stockname);
        int qty = getSharesOwned(stockname);
        return Double.parseDouble(ServletUtilities.toDollarFigures(price*qty));
    }

    private String getUserName(HttpServletRequest request) throws ServletException
    {
        String username=Session.userName(request);
        return username;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
