<%-- 
    Document   : TransactionDetails
    Created on : Apr 1, 2009, 4:59:41 PM
    Author     : owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
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
    </body>
</html>
