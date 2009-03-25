package nbadDB;

import nbad.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{

        String userName = request.getParameter("userName");
        String stockName = request.getParameter("stockName");
        String quantityString = request.getParameter("quantity");

        if (userName == null || stockName == null || quantityString == null) {
            sendPage(response);
            return;
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            sendPage(response);
            return;
        }

        changeHoldings(userName, stockName, quantity);

        sendPage(response);

        Admin.shutDownDB();
    }

    private void sendPage(HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
            out.println("<head><title>View nbad Database</title></head><body>");

            processUsers(out);

            out.println("</body></html>");
        } finally {
            out.close();
        }
    }

    private void processUsers(PrintWriter out) {
        out.println("<p>USERS / CATEGORIES / STOCKS / HOLDINGS</p>");
        ArrayList<User> users = AdminUsers.getAllUsers();

        Iterator userEntries = users.iterator();
        while (userEntries.hasNext()) {
            User user = (User)userEntries.next();
            out.println("<p>---User: " + user.getUserName() + " - " + user.getFullName() + "</p>");
            String userName = user.getUserName();
            processCategories(out, userName);
        }
    }

    private void processCategories(PrintWriter out, String userName) {
        ArrayList<Category> categories = AdminCategories.getAllCategories();

        Iterator categoryEntries = categories.iterator();
        while (categoryEntries.hasNext()) {
            Category category = (Category)categoryEntries.next();
            out.println("<p>------Category: " + category.getName() + "</p>");
            String categoryName = category.getName();
            processStocks(out, userName, categoryName);
        }
    }

    private void processStocks(PrintWriter out, String userName, String categoryName) {
        ArrayList<Stock> stocks = AdminStocks.getAllStocks();

        Iterator stockEntries = stocks.iterator();
        while (stockEntries.hasNext()) {
            Stock stock = (Stock)stockEntries.next();
            if (stock.getCategory().equals(categoryName)) {
                out.println("<form action='ViewDB' method='post'>");
                out.println("<p>");
                out.println("---------Stock: " + stock.getName() + " Price: " + stock.getPrice() + " Shares:");
                String stockName = stock.getName();
                processHoldings(out, userName, stockName);
            }
        }
    }

    private void processHoldings(PrintWriter out, String userName, String stockName) {
        Holding holding = AdminHoldings.getHolding(userName, stockName);
        Integer quantity = 0;
        if (holding != null) {
            quantity = holding.getQuantity();
        }
        out.println("<input name='quantity' type='text' value='" + quantity + "' size='2' />");
        out.println("<input name='userName' type='hidden' value='" + userName + "' />");
        out.println("<input name='stockName' type='hidden' value='" + stockName + "' />");
        out.println("<input name='quantity' type='submit' value='Change' />");
        out.println("</p>");
        out.println("</form>");
    }

    private void changeHoldings(String userName, String stockName, Integer quantity) {
        Holding holding = AdminHoldings.getHolding(userName, stockName);
        if (holding == null) {
            AdminHoldings.addHolding(userName, stockName, quantity);
        } else {
            AdminHoldings.updateHolding(userName, stockName, quantity);
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

