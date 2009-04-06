<%-- 
    Document   : TransactionDetails
    Created on : Apr 1, 2009, 4:59:41 PM
    Author     : owner
--%>

<%@page import="servletUtil.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="stockmart.css"/>
<title>Transaction Details</title></head>
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
        <th colspan="5">Transaction Details for ${userFullName}</th>
        </tr>
        <tr>
        <th>Name</th><th>Action</th><th>Quantity</th><th>Price per Share</th><th>Total Value</th>
        </tr>
        <tr>
        <td>${orderdetails.stockname}</td>
        <td>${orderdetails.action}</td>

        <td><form name="changeform" action="/TransactionDetails" method="post">
                <input name="change" type="text" size="3" value="${orderdetails.quantity}"/>
                <input type="hidden"  name="price" size="3" value="${orderdetails.price}"/>
                <input name="changeSubmit" type="submit" value="Change"/></form>
        </td>
        
        <td>\$${orderdetails.price}</td>
        <td>\$${orderdetails.total}</td>
        </tr>

        
        <tr>            
            <th colspan="5">Bank Account Information</th>
        </tr>

        <form name="t_details" action="/TransactionConfirm" method="post">
        <tr>        
        <td colspan="3">Account Holder Name</td>
        <td colspan="2"><input type="text" value=""/></td>
        </tr>
        <tr>
        <td colspan="3">Routing Number</td>
        <td colspan="2"><input type="text" value=""/></td>
        </tr>
        <tr>
        <td colspan="3">Account Number</td>
        <td colspan="2"><input type="text" value=""/></td>
        </tr>

        <tr>
        <td colspan="4">
        <input type="hidden" name="totalValue" size="3" value="${orderdetails.total}"/>
        <input type="hidden" name="stockname" size="3" value="${orderdetails.stockname}"/>
        <input type="hidden" name="action" size="3" value="${orderdetails.action}"/>
        <input type="hidden" name="price" size="3" value="${orderdetails.price}"/>
        <input type="hidden" name="change" size="3" value="${orderdetails.quantity}"/>
        <input type="submit" value="Confirm Transaction"/>&#32;<input type="reset" value="Reset"/>
        </td>        
        
        <td colspan="1">
            <a href="/Categories">[Categories]</a>&#32;
            <a href="/Login">[Log Out]</a>
        </td>
        </tr>
        </form>

        </table>
        </div><!-- content-->

        <p class="center"><%=ServletUtilities.explanationTransaction%></p>

        <%=ServletUtilities.footer%>
        <%=ServletUtilities.END%>