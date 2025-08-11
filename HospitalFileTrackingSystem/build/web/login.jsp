<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Hospital File Tracking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('1-896cfb11.png') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 40px 20px;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
        }

        h1 {
            margin-bottom: 25px;
            text-align: center;
            color: #333;
        }

        .message {
            background-color: #ffe1e1;
            color: #d8000c;
            padding: 10px 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-top: 15px;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            font-size: 1em;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"], .btn-link {
            margin-top: 25px;
            padding: 12px;
            font-size: 1em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Style links as buttons */
        .btn-link {
            background: #6c757d;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            width: 100%;
            box-sizing: border-box;
        }

        .btn-link:hover {
            background: #5a6268;
        }

        .extra-actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }

        @media (max-width: 400px) {
            .extra-actions {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
    <div class="container">

        <h1>Hospital File Tracking System - LOGIN</h1>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div class="message"><%= message %></div>
        <%
            }
        %>

        <form action="LoginServlet" method="POST" novalidate>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="pswd">Password:</label>
            <input type="password" id="pswd" name="pswd" required>

            <input type="submit" value="LOGIN">
        </form>

    </div>
</body>
</html>
