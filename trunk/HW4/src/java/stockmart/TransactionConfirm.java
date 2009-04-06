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
import nbad.Holding;
import servletUtil.ServletUtilities;
import nbadDB.AdminHoldings;

/**
 *
 * @author Ebonie Williams
 */
public class TransactionConfirm extends HttpServlet {
    private String stockname="";
    private String action="";
    private double price=0.00;
    private int quantity=0;
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
        getInput(request);
        TConfirmBean orderconfirm = new TConfirmBean(stockname, action, price, totalValue, quantity);
        request.setAttribute("orderconfirm", orderconfirm);
        RequestDispatcher rd = request.getRequestDispatcher("/TransactionConfirm.jsp");
        rd.forward(request, response);
        }
    }

    private void getInput(HttpServletRequest request) throws ServletException {
        String username = Session.userName(request);
        if((request.getParameter("stockname"))!=null)
            stockname = request.getParameter("stockname");
        if((request.getParameter("action"))!=null)
            action = request.getParameter("action");

        //a null, malformed input is accounted for, default supplied
        price = ServletUtilities.getDoubleParameter(request, "price", 0.00);        
        quantity = ServletUtilities.getIntParameter(request,"change", 1);

        if((request.getParameter("totalValue"))!=null)
        {
            totalValue = Double.parseDouble(request.getParameter("totalValue"));            
        }

        Holding updateHolding;
        int currentQty=0;
        if(action.equals("Buy"))
        {
            currentQty=getSharesOwned(stockname, username);
            if(AdminHoldings.getHolding(username, stockname)!=null)
                updateHolding = AdminHoldings.updateHolding(username, stockname, currentQty+quantity);
            else
                updateHolding = AdminHoldings.addHolding(username, stockname, quantity);
        }
        else if(action.equals("Sell"))
        {
            currentQty=getSharesOwned(stockname, username);
            if(currentQty<quantity)
                updateHolding = AdminHoldings.updateHolding(username, stockname, currentQty);
            else
                updateHolding = AdminHoldings.updateHolding(username, stockname, currentQty-quantity);
        }        
    }

    private int getSharesOwned(String stockname, String username) throws ServletException {
        Holding hold = AdminHoldings.getHolding(username, stockname);
        if(hold!=null)
        {
            return hold.getQuantity();
        }
        else
        {
            return 0;
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

}
