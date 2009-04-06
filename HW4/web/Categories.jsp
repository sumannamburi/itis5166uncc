<%-- 
    Document   : Categories
    Created on : Apr 1, 2009, 4:58:45 PM
    Author     : owner
--%>

<%@page import="servletUtil.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="stockmart.css"/>
<title>Categories</title></head>
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
        <th colspan="2">Welcome ${userFullName}</th>
        </tr>
        <tr>
        <td>You have a total of \$${catBean.investTotal} invested.</td>
        </tr>
        <tr>
        <td>Select from the below categories</td>
        </tr>
        </table>
        <br/><br/>
        <table border="1" class="full">
        <tr class="heading">
        <th colspan="2">Stock Categories</th>
        </tr>
        <tr>
        <td>Type of Stock</td>
        <td>Amount Invested</td>
        </tr>
        <tr>
        <td><a href="/Energy">Energy</a></td>
        <td>\$${catBean.energyTotal}</td>
        </tr>
        <tr>
        <td><a href="/Transportation">Transportation</a></td>
        <td>\$${catBean.transitTotal}</td>
        </tr>
        <tr>
        <td><a href="/Healthcare">Health Care</a></td>
        <td>$${catBean.healthTotal}</td>
        </tr>
        <tr>
        <td colspan="2"><a href="/Login">[Log Out]</a></td>
        </tr>
        </table>
        </div><!-- content-->
        <%=ServletUtilities.footer%>
    <%=ServletUtilities.END%>
