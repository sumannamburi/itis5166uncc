<%-- 
    Document   : Categories
    Created on : Apr 1, 2009, 4:58:45 PM
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
        out.println(ServletUtilities.headWithTitle("Stock Categories"));
        out.println(ServletUtilities.header("StockMart"));
        out.println("<div id=\"content\">");

        out.println("<table border=\"1\" class=\"full\">");
        out.println("<tr class=\"heading\">");
        out.println("<th colspan=\"2\">Welcome "+userFullName+"</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>You have a total of "+ServletUtilities.toDollarFigures(investTotal)+" invested.</td>");//holding $ total
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Select from the below categories</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br/><br/>");
        out.println("<table border=\"1\" class=\"full\">");
        out.println("<tr class=\"heading\">");
        out.println("<th colspan=\"2\">Stock Categories</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Type of Stock</td>");
        out.println("<td>Amount Invested</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Energy\">Energy</a></td>");
        out.println("<td>$"+ServletUtilities.toDollarFigures(energyTotal)+"</td>");//specific holding total
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Transportation\">Transportation</a></td>");
        out.println("<td>$"+ServletUtilities.toDollarFigures(transitTotal)+"</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td><a href=\""+ServletUtilities.PATHROOT+"/Healthcare\">Health Care</a></td>");
        out.println("<td>$"+ServletUtilities.toDollarFigures(healthTotal)+"</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan=\"2\"><a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</div><!-- content-->");
        out.println(ServletUtilities.footer);
        out.println(ServletUtilities.END);
    </body>
</html>
