/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servletUtil.ServletUtilities;
import nbad.*;

/**
 *
 * @author Ebonie Williams
 */
public class Login extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void displayLogin(HttpServletRequest request, HttpServletResponse response, String message, String userName)
    throws ServletException, IOException {

        LoginBean loginbean = new LoginBean(message);
        request.setAttribute("loginbean", loginbean);
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
   
    }

    
    private void validate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username=null, password=null;
        if(request.getParameter("username")!=null)
            username = ServletUtilities.filter(request.getParameter("username"));
        if(request.getParameter("password")!=null)
            password = ServletUtilities.filter(request.getParameter("password"));
        

        //CHECK FOR MISSING INPUTS, VALIDATE USER
        if(username.length()==0)
        {
            displayLogin(request, response, "The username was not entered.  Please try again.", "");
        }
        else if(password.length()==0)
        {
            displayLogin(request, response, "The password was not entered.  Please try again.", "");
        }
        //System.out.println(UserAccessControl.getUser(username));
        else if(UserAccessControl.isValidLogin(username, password) == false)
        {
            displayLogin(request, response, "The username or password is not valid.  You may need to register first.  Please try again.", "");
        }
        else
        {
            User user = UserAccessControl.getUser(username);
            HttpSession session = Session.getSession(request);
            session.setAttribute(Session.userSession, user);
            session.setAttribute(Session.isLoggedIn, "logged");
            if(request.getParameter("remind")!=null)
            {
                if(request.getParameter("remind").equals("yes"))
                {
                    session.setAttribute(Session.rememberMe, "yes");
                    //create cookie
                    Cookie c = new Cookie(Session.remind, user.getUserName());//in case of browser restart
                    c.setMaxAge(ServletUtilities.LONGLIFECOOKIE);
                    response.addCookie(c);
                }

            }
            
            response.setStatus(response.SC_FOUND);
            response.sendRedirect(ServletUtilities.PATHROOT+"/Categories");
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
       Session.doRememberUser(request);

       
       if(Session.getSession(request).isNew())
           displayLogin(request, response,"", "");
       else
       {
           String value = Session.getCookie(request.getCookies(), Session.remind).getValue();
           if(value == null)
           {
               value = "";
           }
           displayLogin(request, response,"", value);
       }
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
