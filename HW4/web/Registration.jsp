<%-- 
    Document   : Registration
    Created on : Apr 1, 2009, 4:58:27 PM
    Author     : owner
--%>

<%@page import="servletUtil.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html\n"+
"PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"\n"+
""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="stockmart.css"/>
<title>Registration</title></head>

    <body>
        <div id="header">
        <p class="banner">Stockmart</p>
        </div><!--header-->

        <br/>
        <hr/>
        <br/>
        
        <div id="content">
        <p>${regMessage}</p>
        <form name="register" action="/Registration" method="post">
        <table border="1" class="login">
        <tr class="heading">
        <th colspan="2">Registration</th>
        </tr>

        <tr>
        <td>First Name:</td>
        <td><input type="text" name="firstname" id="firstname"/></td>
        </tr>

        <tr>
        <td>Last Name:</td>
        <td><input type="text" name="lastname" id="lastname"/></td>
        </tr>

        <tr>
        <td>Requested Username:</td>
        <td><input type="text" name="username" id="username" /></td>
        </tr>

        <tr>
        <td>Password:</td>
        <td><input type="password" name="pw" id="pw"/></td>
        </tr>
        <tr>
        <td>Confirm Password:</td>
        <td><input type="password" name="pwconfirm" id="pwconfirm"/></td>
        </tr>
        <tr>
        <td colspan="2"><input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/></td>
        </tr>
        </table>
        </form>

        </div><!-- content-->
        ServletUtilities.footer
        ServletUtilities.END

    </body>
</html>
