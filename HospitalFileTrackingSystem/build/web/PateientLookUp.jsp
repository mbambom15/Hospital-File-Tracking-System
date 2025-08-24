<%-- 
    Document   : PateientLookUp
    Created on : 14 Aug 2025, 07:01:37
    Author     : nhlak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Look Up page</title>
    </head>
    <body>
        <h1>Patient Look Up</h1>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <p><%=message%></p>
        <%
            }
        %>
        <div align="center">
            <form action="LookUpServlet" method="POST">
                <table>
                    <tr>
                        <td>File ID (optional): </td>
                        <td><input type="text" name="id" ></td>
                    </tr>
                    <tr>
                        <td>South African ID: </td>
                        <td><input type="text" name="SA_ID" required=""></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="SEARCH"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
