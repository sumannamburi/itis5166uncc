<%-- 
    Document   : TransactionConfirm
    Created on : Apr 1, 2009, 4:59:53 PM
    Author     : owner
--%>

<%@page import="servletUtil.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="stockmart.css"/>
<title>Transaction Confirm</title></head>
    <body>
        <div id="header">
        <p class="banner">Stockmart</p>
        </div><!--header-->

        <br/>
        <hr/>
        <br/>
        <div id="content">
        <table border="1" class="full">
        <tr class="heading" >
        <th colspan="5">Transaction Details for ${userFullName}</th>
        </tr>
        <tr>
        <th>Name</th><th>Action</th><th>Quantity</th><th>Price per Share</th><th>Total Value</th>
        </tr>
        <tr>
        <td>${orderconfirm.stockname}</td><td>${orderconfirm.action}</td><td>${orderconfirm.quantity}</td><td>\$${orderconfirm.price}</td>
                <td>\$${orderconfirm.total}</td>
        </tr>
        <tr><td colspan="4"></td>
        <td colspan="1"><a href="/Categories">
                [Categories]</a>&#32;<a href="/Login">[Log Out]</a></td>
        </tr></table></div><!-- content-->
        <%=ServletUtilities.footer%>
        <%=ServletUtilities.END%>