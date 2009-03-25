/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nbad.User;
/**
 *
 * @author Ebonie Williams
 */
public class Session
{

    public static final String isLoggedIn="isLoggedIn";
    public static final String userSession="userSession";
    public static final String rememberMe = "rememberMe";
    public static final String userName = "userName";
    private static boolean cookieGotIt = false;
    public static final String remind="remind";

    /**
     * Gets the session, creates a new one if null
     *
     * @param request
     * @return HttpSession session
     */
    public static HttpSession getSession(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        return session;
    }



    /**
     * Invalidates the current session
     * @param request
     */
    static void deleteSession(HttpServletRequest request)
            throws ServletException, IOException
    {
        getSession(request).invalidate();
    }

    
    static boolean hasLoggedIn(HttpServletRequest request)throws ServletException
    {
        boolean hasLoggedIn = false;
        if(getSession(request).getAttribute(isLoggedIn)=="logged")
            hasLoggedIn = true;
        return hasLoggedIn;
    }

    static String userFullName(HttpServletRequest request)throws ServletException
    {
        String fullName = null;
        HttpSession session = getSession(request);
        User loginSession = (User) session.getAttribute(userSession);
        fullName = loginSession.getFullName();
        return fullName;
    }

    static String userName(HttpServletRequest request)throws ServletException
    {
        String fullName = null;
        HttpSession session = getSession(request);
        User loginSession = (User) session.getAttribute(userSession);
        fullName = loginSession.getUserName();
        return fullName;
    }

    /**
     * If this box is checked at login, then the next time the application 
     * will automatically populate the userName field on the login page with 
     * the username of the last user that logged in, even if the browser 
     * has been restarted.
     *
     * Checks the cookie, if available, and session attribute rememberMe, if available,
     * to see if the user wanted to be remember
     * If so, then their username is saved as a session attribute (this happens here)
     * If not, the entire session is invalidate only
     * @param request
     * @return
     * @throws javax.servlet.ServletException
     */
    static void doRememberUser(HttpServletRequest request)throws ServletException, IOException
    {
       HttpSession session = getSession(request);
       Cookie [] cookies = request.getCookies();
       
       if(cookies!=null)
       {
           if(getCookie(cookies, rememberMe, "yes")!=null)
           {
               User user = (User) session.getAttribute(userSession);
               session.setAttribute(userName, user.getUserName());
               session.removeAttribute(userSession);
               session.removeAttribute(isLoggedIn);
               cookieGotIt = true;
           }
       }
       if(cookieGotIt == false)
       {
           if(session.getAttribute(rememberMe)!=null)
           {
               if(session.getAttribute(rememberMe).equals("yes"))
               {
                   User user = (User) session.getAttribute(userSession);
                   session.setAttribute(userName, user.getUserName());
                   session.removeAttribute(userSession);
                   session.removeAttribute(isLoggedIn);
               }
           }
           else
           {
               deleteSession(request);
           }
       }       
    }

    static Cookie getCookie(Cookie[] cookies, String cookiename, String cookievalue)
    {
       if(cookies!=null)
       {
           for(int j=0; j<cookies.length; j++)
           {
               Cookie c = cookies[j];
               if(c.getName().equals(cookiename))
               {
                   if(c.getValue().equals(cookievalue))
                   {
                       return c;
                   }
               }
           }
       }
       return null;
    }

    static Cookie getCookie(Cookie[] cookies, String cookiename)
    {
       if(cookies!=null)
       {
           for(int j=0; j<cookies.length; j++)
           {
               Cookie c = cookies[j];
               if(c.getName().equals(cookiename))
               {
                   return c;
               }
           }
       }
       return null;
    }

}
