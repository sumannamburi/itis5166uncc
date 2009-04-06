/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
    private double totalValue=0.00;
   
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

        TransactionBean orderdetails = new TransactionBean(stockname, action, price, totalValue, quantity);
        request.setAttribute("orderdetails", orderdetails);
        RequestDispatcher rd = request.getRequestDispatcher("TransactionDetails.jsp");
        rd.forward(request, response);
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
        totalValue = Double.parseDouble(ServletUtilities.toDollarFigures(price * quantity));
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


