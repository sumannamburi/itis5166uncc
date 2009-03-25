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
        PrintWriter out = response.getWriter();
        try {
            out.println(ServletUtilities.headWithTitle("Energy Stocks"));
            out.println(ServletUtilities.header("StockMart"));
            out.println("<div id=\"content\">");
            
            out.println("<table border=\"1\" class=\"full\">");
            out.println("<tr class=\"heading\">");
            out.println("<th colspan=\"7\">Energy Stocks</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Shares Owned</th>");
            out.println("<th>Price per Share</th>");
            out.println("<th>Total Value</th>");
            out.println("<th>Action</th>");
            out.println("<th>Quantity</th>");
            out.println("<th></th>");
            out.println("</tr>");

            for(int i=0; i<energyStocks.length;i++){
            out.println("<tr><form name=\"energy\" action=\""+ServletUtilities.PATHROOT+"/TransactionDetails\" method=\"post\">");
            
            out.println("<td>"+energyStocks[i]+"</td>");
            out.println("<td>"+getSharesOwned(energyStocks[i])+"</td>");
            out.println("<td>$"+getSharePrice(energyStocks[i])+"</td>");
            out.println("<td>$"+getTotal(energyStocks[i])+"</td>");
            out.println("<td>");
            out.println("<select name=\"action\"><option value=\"Buy\">Buy</option><option value=\"Sell\">Sell</option></select>");
            out.println("</td>");
            out.println("<td><input type=\"text\" name=\"quantity\" size=\"3\" value=\"0\"/></td>");
            out.println("<td>" +
                    "<input type=\"hidden\" name=\"stockname\" size=\"3\" value=\""+energyStocks[i]+"\"/>" +
                    "<input type=\"hidden\" name=\"price\" size=\"3\" value=\""+getSharePrice(energyStocks[i])+"\"/>" +
                    "<input type=\"submit\" value=\"Make Transaction\"/></td></form>");
            out.println("</tr>");
            }

            out.println("<tr>");
            out.println("<td colspan=\"5\">User: "+userFullName+"</td>");
            out.println("<td colspan=\"2\"><a href=\""+ServletUtilities.PATHROOT+"/Categories\">[Categories]</a>");
            out.println("&#32;<a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
            out.println("</tr>");
            out.println("</table>");

            out.println("<p class=\"center\">"+ServletUtilities.explanation+"</p>");
            
            out.println("</div><!-- content-->");
            out.println(ServletUtilities.footer);
            out.println(ServletUtilities.END);
           
        } finally { 
            out.close();
        }

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
