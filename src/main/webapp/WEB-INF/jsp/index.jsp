<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <style>
        th, td {
            text-align: left;
        }
    </style>
</head>
<body>
<p>Welcome </p>
<br>

<br>
<form id="homeLogin" action="login">
    <table>
        <tr>
            <th>
                <label for="emailAddress">Email Address: </label>
            </th>
            <td>
                <input type="text" name="emailAddress" id="emailAddress">
            </td>
        </tr>
        <tr>
            <th>
                <label for="password">Password: </label>
            </th>
            <td>
                <input type="text" name="password" id="password">
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Submit">
    <br>
</form>
<br>
<%
    out.println(request.getAttribute("message1"));
%>
<br>
<%
    out.print(request.getAttribute("message2"));
%>
<br>
</body>
</html>
