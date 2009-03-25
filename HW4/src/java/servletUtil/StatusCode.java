/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servletUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


/**
 *
 * @author Ebonie Williams
 */
public class StatusCode {

    public static void errorNotFound(HttpServletResponse response, String msg)
        throws IOException{
        response.sendError(response.SC_NOT_FOUND, msg);
    }

}

