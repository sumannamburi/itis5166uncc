<%-- 
    Document   : Energy
    Created on : Apr 1, 2009, 4:59:05 PM
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
        out.println(ServletUtilities.headWithTitle("Energy Stocks"));
        out.println(ServletUtilities.header("StockMart"));
        out.println("<div id=\"content\">");

        out.println("<table border=\"1\" class=\"full\">");
        out.println("<tr class=\"heading\">");
        out.println("<th colspan=\"7\">Energy Stocks</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Shares Owned</th>");
        out.println("<th>Price per Share</th>");
        out.println("<th>Total Value</th>");
        out.println("<th>Action</th>");
        out.println("<th>Quantity</th>");
        out.println("<th></th>");
        out.println("</tr>");

        for(int i=0; i<energyStocks.length;i++){
        out.println("<tr><form name=\"energy\" action=\""+ServletUtilities.PATHROOT+"/TransactionDetails\" method=\"post\">");

        out.println("<td>"+energyStocks[i]+"</td>");
        out.println("<td>"+getSharesOwned(energyStocks[i])+"</td>");
        out.println("<td>$"+getSharePrice(energyStocks[i])+"</td>");
        out.println("<td>$"+getTotal(energyStocks[i])+"</td>");
        out.println("<td>");
        out.println("<select name=\"action\"><option value=\"Buy\">Buy</option><option value=\"Sell\">Sell</option></select>");
        out.println("</td>");
        out.println("<td><input type=\"text\" name=\"quantity\" size=\"3\" value=\"0\"/></td>");
        out.println("<td>" +
                "<input type=\"hidden\" name=\"stockname\" size=\"3\" value=\""+energyStocks[i]+"\"/>" +
                "<input type=\"hidden\" name=\"price\" size=\"3\" value=\""+getSharePrice(energyStocks[i])+"\"/>" +
                "<input type=\"submit\" value=\"Make Transaction\"/></td></form>");
        out.println("</tr>");
        }

        out.println("<tr>");
        out.println("<td colspan=\"5\">User: "+userFullName+"</td>");
        out.println("<td colspan=\"2\"><a href=\""+ServletUtilities.PATHROOT+"/Categories\">[Categories]</a>");
        out.println("&#32;<a href=\""+ServletUtilities.PATHROOT+"/Login\">[Log Out]</a></td>");
        out.println("</tr>");
        out.println("</table>");

        out.println("<p class=\"center\">"+ServletUtilities.explanation+"</p>");

        out.println("</div><!-- content-->");
        out.println(ServletUtilities.footer);
        out.println(ServletUtilities.END);

    </body>
</html>
