<%-- 
    Document   : error
    Created on : 22-gen-2020, 2.58.55
    Author     : ciro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String msg =
                    (String) request.getAttribute("msg");
         %>
         <h1><%=msg%></h1>
         <a href=".">Home</a>
    </body>
</html>
