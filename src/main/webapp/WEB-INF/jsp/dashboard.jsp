<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        th, td {
            text-align: left;

        }
    </style>
</head>
<body>
<table>
    <%
        File[] files = (File[]) request.getAttribute("files");
        for (File file : files) {%>
    <tr>
        <th>
            <label><%out.print(file.getName());%></label>
        </th>
        <td>
            <label><%out.print(file.getAbsolutePath());%></label>
        </td>
    </tr>
    <%
        }
    %>
</table>
<form action="uploadForm" method="get">
    <button type="submit">Upload</button>
</form>
</body>
</html>
