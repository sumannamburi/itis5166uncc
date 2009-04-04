/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Energy extends HttpServlet{
    private String [] energyStocks = {DefaultStocks.ABCG, DefaultStocks.PSOL};
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
        

        }
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

    private double getSharePrice(String stockname) {
        Stock stock = AdminStocks.getStock(stockname);
        if(stock!=null)
        {
            return stock.getPrice();
        }
        else
        {
            return 0;
        }
    }

    private int getSharesOwned(String stockname) throws ServletException {
        Holding hold = AdminHoldings.getHolding(getUserName(requestStock), stockname);
        if(hold!=null)
        {
            return hold.getQuantity();
        }
        else
        {
            return 0;
        }
    }

    private String getTotal(String stockname) throws ServletException {
        double price = getSharePrice(stockname);
        int qty = getSharesOwned(stockname);
        return ServletUtilities.toDollarFigures(price*qty);
    }

    private String getUserName(HttpServletRequest request) throws ServletException
    {
        String username=Session.userName(request);
        return username;
    }

    

}
