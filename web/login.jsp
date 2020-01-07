<%-- 
    Document   : login
    Created on : 4-gen-2020, 14.51.21
    Author     : ciro
--%>

<%@page import="model.managers.AuthenticationManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>
        
        <div id="login">
            
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="performlogin" method="post" style="margin-top: 50%">
                                <%
                                    String target = (String) request.getAttribute("target");
                                    String loginMSG = " ";
                                    if(target != null)
                                        if(target.equals("areariservata"))
                                            loginMSG += "Titolare";
                                        else loginMSG += "Staff";
                                %>
                                <h3 class="text-center text-info">Login<%=loginMSG%></h3>
                                <%
                                    String errMSG = (String) request.getAttribute("errMSG");
                                    if(errMSG != null) {
                                %>
                                <div><%=errMSG%></div>
                                <%}%>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control">
                                </div>
                                <%
                                    if(target == null) {
                                %>
                                <input type="radio" name="auth-type-request" value="<%=AuthenticationManager.TITOLARE%>" > Titolare<br>
                                <input type="radio" name="auth-type-request" value="<%=AuthenticationManager.STAFF%>"> Staff<br>
                                <%
                                    }
                                    else {
                                        if(target.equals("areariservata")) {
                                %>
                                <input type="hidden" name="auth-type-request" value="<%=AuthenticationManager.TITOLARE%>">
                                <%
                                        }
                                        else {
                                %>
                                <input type="hidden" name="auth-type-request" value="<%=AuthenticationManager.STAFF%>">
                                <%
                                        }
                                %>
                                <input type="hidden" name="target" value="<%=target%>">
                                <%
                                    }
                                %>
                                       
                                <div class="form-group">
                                    <input type="submit" name="submit" class="btn btn-info btn-md" value="Entra">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                
        <%@include file="imports.html" %>
    </body>
</html>