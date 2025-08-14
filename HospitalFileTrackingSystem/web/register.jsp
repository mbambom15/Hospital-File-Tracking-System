<%-- 
    Document   : register
    Created on : 14 Aug 2025, 07:42:30
    Author     : nhlak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Sign Up</title>
    </head>
    <style>
         
    </style>
    <body>
        <h1>Register a new file for client</h1>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <p><%=message%></p>
        <%
            }
        %>
        <form action="RegisterPatient" method="POST">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" required=""></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td><input type="text" name="surname" required=""></td>
                </tr>
                <tr>
                    <td>South African ID:</td>
                    <td><input type="text" name="sa_id" required=""</td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><textarea name="address" required=""></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="REGISTER"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
