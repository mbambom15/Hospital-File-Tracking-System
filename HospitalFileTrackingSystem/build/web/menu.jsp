<%-- 
    Document   : menu
    Created on : 14 Aug 2025, 07:42:03
    Author     : nhlak
--%>

<%@page import="za.ac.tut.entities.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Page</title>
    </head>
    <body>
        <%
            Patient patient = (Patient) session.getAttribute("patient");
            String name = patient.getName();
            String surname = patient.getSurname();
            Long id = patient.getId();
        %>
        <h1>The file has been found: <%=name%> <%=surname%> with file number: <%=id%></h1>
        <%
            String message = (String)request.getAttribute("message");
            if(message != null){
            %>
            <p><%=message%></p>
            <%
            }
        %>

        <div class="container">
            <ul>
                <li><a href="CheckServlet">Appointment / Check-Ups: </a></li>
                <li><a href="HistoryServlet"> </a></li>
                <li><a href="prescriptionsServlet">Prescriptions: </a></li>
                <li></li>
                <li><a href="payement">Billing / Payment: </a></li>
            </ul>
        </div>

    </body>
</html>
