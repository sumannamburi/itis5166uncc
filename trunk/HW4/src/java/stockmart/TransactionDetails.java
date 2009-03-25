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
import nbadDB.AdminHoldings;
import servletUtil.ServletUtilities;

/**
 *
 * @author Ebonie Williams
 */
public class TransactionDetails extends HttpServlet {

    private String stockname="";
    private String action="";
    private int quantity=0;
    private double price=0.00;
    private String totalValue="0.00";
   
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
        getInput(request, Session.userName(request));
        String userFullName = Session.userFullName(request);
        PrintWriter out = response.getWriter();
        try {
            out.println(ServletUtilities.headWithTitle("Transaction Details"));
            out.println(ServletUtilities.header("StockMart"));
            out.println("<div id=\"content\">");
            
            
            out.println("<table border=\"1\" class=\"full\">");
            out.println("<tr class=\"heading\" >");
            out.println("<th colspan=\"5\">Transaction Details for "+userFullName+"</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Name</th><th>Action</th><th>Quantity</th><th>Price per Share</th><th>Total Value</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>"+stockname+"</td><td>"+action+"</td>");

            out.println("<td><form name=\"changeform\" action=\""+ServletUtilities.PATHROOT+"/TransactionDetails\" method=\"post\">" +
                    "<input name=\"change\" type=\"text\" size=\"3\" value=\""+quantity+"\"/>" +
                    "<input type=\"hidden\"  name=\"price\" size=\"3\" value=\""+price+"\"/>"+
                    "<input name=\"changeSubmit\" type=\"submit\" value=\"Change\" /></form></td>");
            
            out.println("");
            out.println("<td>$"+price+"" +
                    "</td><td>$"+totalValue+"" +
                    "</td>");

            out.println("</tr>");
            out.println("<tr><form name=\"t_details\" action=\""+ServletUtilities.PATHROOT+"/" +
                    "TransactionConfirm\" method=\"post\"><th colspan=\"5\">Bank Account Information</th></tr>");
            out.println("<tr>");
            out.println("<td colspan=\"3\">Account Holder Name</td>");
            out.println("<td colspan=\"2\"><input type=\"text\" value=\"\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"3\">Routing Number</td>");
            out.println("<td colspan=\"2\"><input type=\"text\" value=\"\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"3\">Account Number</td>");
            out.println("<td colspan=\"2\"><input type=\"text\" value=\"\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"4\">" +
                    "" +
                    "<input type=\"hidden\" name=\"totalValue\" size=\"3\" value=\""+totalValue+"\"/>" +
                    "<input type=\"hidden\" name=\"stockname\" size=\"3\" value=\""+stockname+"\"/>" +
                    "<input type=\"hidden\" name=\"action\" size=\"3\" value=\""+action+"\"/>" +
                    "<input type=\"hidden\" name=\"price\" size=\"3\" value=\""+price+"\"/>" +
                    "<input type=\"hidden\" name=\"change\" size=\"3\" value=\""+quantity+"\"/>");
            out.println("<input type=\"submit\" value=\"Confirm Transaction\"/>&#32;<input type=\"reset\" value=\"Reset\"/>");
            out.println("</td></form>");
            out.println("");
            out.println("<td colspan=\"1\"><a href=\""+ServletUtilities.PATHROOT+"/Categories\">[Categories]</a>&#32;<a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
            out.println("</tr></table></div><!-- content-->");

            out.println("<p class=\"center\">"+ServletUtilities.explanationTransaction+"</p>");

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


    private void getInput(HttpServletRequest request, String username) {
        if(request.getParameter("stockname")!=null)
            stockname = request.getParameter("stockname");
        if(request.getParameter("action")!=null)
            action = request.getParameter("action");

        //a null, malformed input is accounted for, default supplied
        if(request.getParameter("change")!=null)
        {
            if(action.equals("Sell"))
            {
                if(isSellFraud(username, stockname))
                {
                    quantity=0;
                }
                else
                {
                    quantity = ServletUtilities.getIntParameter(request,"change", 1);
                    if(isSellOver(username, stockname))
                    {
                        quantity=0;
                    }
                    /*else
                    {
                        quantity = ServletUtilities.getIntParameter(request,"change", 1);
                    }*/
                }
            }
            else//if not Sell action
            {
                quantity = ServletUtilities.getIntParameter(request,"change", 1);
            }
        }
        else//change is null
        {
            if(action.equals("Sell"))
            {
                if(isSellFraud(username, stockname))
                {
                    quantity=0;
                }
                else
                {
                    quantity = ServletUtilities.getIntParameter(request,"quantity", 1);
                    if(isSellOver(username, stockname))
                    {
                        quantity=0;
                    }
                    /*else
                    {
                        quantity = ServletUtilities.getIntParameter(request,"quantity", 1);
                    }*/
                }
            }
            else//if action isn't sell
                quantity = ServletUtilities.getIntParameter(request,"quantity", 1);
        }
        price = ServletUtilities.getDoubleParameter(request, "price", 0.00);
        totalValue = ServletUtilities.toDollarFigures(price * quantity);
    }

    /**
     * Returns true if the user is trying to sell stocks they don't have
     * @param username
     * @param stockname
     * @return
     */
    private boolean isSellFraud(String username, String stockname)
    {
        if(AdminHoldings.getHolding(username, stockname)!=null)
            return false;
        else
            return true;
    }
    /**
     * Returns true if the user is trying to sell more stocks than they have
     * @param username
     * @param stockname
     * @return
     */
    private boolean isSellOver(String username, String stockname)
    {
        if(AdminHoldings.getHolding(username, stockname).getQuantity()<quantity)
            return true;
        else
            return false;
    }
}


