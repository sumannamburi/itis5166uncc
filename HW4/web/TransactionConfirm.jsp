<%-- 
    Document   : TransactionConfirm
    Created on : Apr 1, 2009, 4:59:53 PM
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
        out.println(ServletUtilities.headWithTitle("Transaction Confirm"));
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
        out.println("<td>"+stockname+"</td><td>"+action+"</td><td>"+quantity+"</td><td>$"+price+"</td>" +
                "<td>$"+ServletUtilities.toDollarFigures(totalValue)+"</td>");
        out.println("</tr>");
        out.println("<tr><td colspan=\"4\"></td>");
        out.println("<td colspan=\"1\"><a href=\""+ServletUtilities.PATHROOT+"/Categories\">" +
                "[Categories]</a>&#32;<a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
        out.println("</tr></table></div><!-- content-->");
        out.println(ServletUtilities.footer);
        out.println(ServletUtilities.END);
    </body>
</html>
