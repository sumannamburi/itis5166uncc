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
public class Categories extends HttpServlet {

    private double energyTotal=0;
    private double healthTotal=0;
    private double transitTotal=0;
    private double investTotal=0;
    private String userFullName="";
    private HttpServletRequest requestCategories;
   
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
        requestCategories = request;
        userFullName = Session.userFullName(request);
        calculateEnergy();
        calculateHealthcare();
        calculateTransportation();
        calculateInvestTotal();
        PrintWriter out = response.getWriter();
        try {
            out.println(ServletUtilities.headWithTitle("Stock Categories"));
            out.println(ServletUtilities.header("StockMart"));
            out.println("<div id=\"content\">");
            
            out.println("<table border=\"1\" class=\"full\">");
            out.println("<tr class=\"heading\">");
            out.println("<th colspan=\"2\">Welcome "+userFullName+"</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>You have a total of "+ServletUtilities.toDollarFigures(investTotal)+" invested.</td>");//holding $ total
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Select from the below categories</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<br/><br/>");
            out.println("<table border=\"1\" class=\"full\">");
            out.println("<tr class=\"heading\">");
            out.println("<th colspan=\"2\">Stock Categories</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Type of Stock</td>");
            out.println("<td>Amount Invested</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Energy\">Energy</a></td>");
            out.println("<td>$"+ServletUtilities.toDollarFigures(energyTotal)+"</td>");//specific holding total
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Transportation\">Transportation</a></td>");
            out.println("<td>$"+ServletUtilities.toDollarFigures(transitTotal)+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Healthcare\">Health Care</a></td>");
            out.println("<td>$"+ServletUtilities.toDollarFigures(healthTotal)+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"2\"><a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</div><!-- content-->");
            out.println(ServletUtilities.footer);
            out.println(ServletUtilities.END);
        } finally { 
            out.close();
        }
        }
    }

    public void calculateEnergy() throws ServletException
    {
        energyTotal = calculateBackend(DefaultStocks.ABCG)
                +calculateBackend(DefaultStocks.PSOL);
    }

    public double calculateBackend(String stockname) throws ServletException
    {
        Holding hold = AdminHoldings.getHolding(getUserName(requestCategories), stockname);
        if(hold!=null)
        {
        int sumQty = hold.getQuantity();
        Stock estock = AdminStocks.getStock(hold.getStockName());
        double price=estock.getPrice();
        double total = sumQty*price;
        return total;
        }
        else
        {
            return 0;
        }
    }

    public void calculateHealthcare() throws ServletException
    {
        healthTotal = calculateBackend(DefaultStocks.ABCP)
                +calculateBackend(DefaultStocks.PMED);
    }

    public void calculateTransportation() throws ServletException
    {
        transitTotal = calculateBackend(DefaultStocks.CBUS)
                +calculateBackend(DefaultStocks.COMR);
    }

    public void calculateInvestTotal()
    {
        investTotal = energyTotal+healthTotal+transitTotal;
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
