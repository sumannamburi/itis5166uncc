<%-- 
    Document   : Energy
    Created on : Apr 1, 2009, 4:59:05 PM
    Author     : owner
--%>

<%@page import="servletUtil.*,nbadDB.DefaultStocks" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="stockmart.css"/>
<title>Energy</title></head>
    <body>
        <div id="header">
        <p class="banner">Stockmart</p>
        </div><!--header-->

        <br/>
        <hr/>
        <br/>
        <div id="content">

        <table border="1" class="full">
        <tr class="heading">
        <th colspan="7">Energy Stocks</th>
        </tr>
        <tr>
        <th>Name</th>
        <th>Shares Owned</th>
        <th>Price per Share</th>
        <th>Total Value</th>
        <th>Action</th>
        <th>Quantity</th>
        <th></th>
        </tr>

        <c:forEach var="stock" items="${stocks}">
        <tr><form name="stock" action="/TransactionDetails" method="post">
        <td>${stock.name}</td>
        <td>${numberOfSharesOwned[stock.name]}</td>
        <td>\$${pricePerOwned[stock.name]}</td>
        <td>\$${totalPerStock[stock.name]}</td>
        <td>
        <select name="action"><option value="Buy">Buy</option><option value="Sell">Sell</option></select>
        </td>
        <td><input type="text" name="quantity" size="3" value="0"/></td>
        <td> 
        <input type="hidden" name="stockname" size="3" value="${stock.name}"/> 
        <input type="hidden" name="price" size="3" value="${pricePerOwned[stock.name]}"/> 
        <input type="submit" value="Make Transaction"/></td></form>
        </tr>
        </c:forEach>



        <tr>
        <td colspan="5">User: ${userFullName}</td>
        <td colspan="2"><a href="/Categories">[Categories]</a>
        &#32;<a href="/Login">[Log Out]</a></td>
        </tr>
        </table>

        <p class="center"><%=ServletUtilities.explanation%></p>

        </div><!-- content-->
        <%=ServletUtilities.footer%>
        <%=ServletUtilities.END%>
