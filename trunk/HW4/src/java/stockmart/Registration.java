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
import javax.servlet.http.HttpSession;
import nbad.*;
import servletUtil.ServletUtilities;
/**
 *
 * @author Ebonie Williams
 */
public class Registration extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void displayRegistration(HttpServletRequest request, HttpServletResponse response, String message)
    throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
            
            out.println(ServletUtilities.headWithTitle("Registration"));
            out.println(ServletUtilities.header("StockMart"));
            out.println("<div id=\"content\">");
            out.println("<p>"+message+"</p>");
            out.println("<form name=\"register\" action=\""+ServletUtilities.PATHROOT+"/Registration\" method=\"post\">");
            out.println("<table border=\"1\" class=\"login\">");
            out.println("<tr class=\"heading\">");
            out.println("<th colspan=\"2\">Registration</th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>First Name:</td>");
            out.println("<td><input type=\"text\" name=\"firstname\" id=\"firstname\"/></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Last Name:</td>");
            out.println("<td><input type=\"text\" name=\"lastname\" id=\"lastname\"/></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Requested Username:</td>");
            out.println("<td><input type=\"text\" name=\"username\" id=\"username\" /></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Password:</td>");
            out.println("<td><input type=\"password\" name=\"pw\" id=\"pw\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Confirm Password:</td>");
            out.println("<td><input type=\"password\" name=\"pwconfirm\" id=\"pwconfirm\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"2\"><input type=\"submit\" value=\"Submit\"/>");
            out.println("<input type=\"reset\" value=\"Reset\"/></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");

            out.println("</div><!-- content-->");
            out.println(ServletUtilities.footer);
            out.println(ServletUtilities.END);

        } finally { 
            out.close();
        }
    }


    
    
    private void validate (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username=null, password=null, firstname = null, lastname=null, pwconfirm=null;
        if(request.getParameter("username")!=null)
            username = ServletUtilities.filter(request.getParameter("username"));
        if(request.getParameter("pw")!=null)
            password = ServletUtilities.filter(request.getParameter("pw"));
        if(request.getParameter("firstname")!=null)
            firstname = ServletUtilities.filter(request.getParameter("firstname"));
        if(request.getParameter("lastname")!=null)
            lastname = ServletUtilities.filter(request.getParameter("lastname"));
        if(request.getParameter("pwconfirm")!=null)
            pwconfirm = ServletUtilities.filter(request.getParameter("pwconfirm"));

        //CHECK FOR MISSING INPUTS
        if(username.length()==0)
        {
            displayRegistration(request, response, "The username was not entered.  Please try again.");
        }
        if(password.length()==0)
        {
            displayRegistration(request, response, "The password was not entered.  Please try again.");
        }
        if(firstname.length()==0)
        {
            displayRegistration(request, response, "The first name was not entered.  Please try again.");
        }
        if(lastname.length()==0)
        {
            displayRegistration(request, response, "The last name was not entered.  Please try again.");
        }

        if(pwconfirm.length()==0)
        {
            displayRegistration(request, response, "Please be sure to confirm your password.");
        }
        else
        {
            if(password.equals(pwconfirm)!=true)
            {
                displayRegistration(request, response, "The password and password confirmation are not the same.  Please try again.");
            }
            else
            {
                //VERIFY THAT USERNAME IS NOT USED
                if(UserAccessControl.isUserNameInUse(username) == false)
                {
                    UserAccessControl.setUser(username, password, firstname, lastname);
                    User user = UserAccessControl.getUser(username);
                    HttpSession session = Session.getSession(request);
                    session.setAttribute(Session.userSession, user);
                    session.setAttribute(Session.isLoggedIn, "logged");
                    response.setStatus(response.SC_FOUND);
                    response.sendRedirect(ServletUtilities.PATHROOT+"/Categories");
                }
                else
                {
                    displayRegistration(request, response, "The username, "+username+", already exists.  Please use a different one.");
                }
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
        displayRegistration(request, response, "");
        validate(request, response);
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
        validate(request, response);
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
