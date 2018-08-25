<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        td {
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
        <td>
            <a href="loadVideoFile?path=<%out.print(file.getAbsolutePath());%>&fileName=<%
                out.print(file.getName());%>"><%out.print(file.getName());%></a>
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
