<%-- 
    Document   : conti
    Created on : 7-gen-2020, 22.57.30
    Author     : ciro
--%>

<%@page import="model.entities.Conto"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>
        <div class="payement" style="margin-top:3%;margin-left:10%;margin-right:10%;">
            <h2 style="margin-left:37%; margin-bottom:4%">Richiesta di Pagamenti</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Tavolo</th>
                        <th scope="col">Totale Spesa</th>
                        <th scope="col">Metodo di Pagamento</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody id="contenitore-conti">
                    <%
                        Collection<Conto> conti = (Collection<Conto>) request.getAttribute("conti");
                        for(Conto conto:conti) {
                    %>
                    <tr>
                        <th scope="row"><%=conto.getTavolo()%></th>
                        <td><%=conto.getTotale()%></td>
                        <td><%=conto.getMetodo()%></td>
                        <td>
                            <form action="rimuoviconto" method="GET">
                                <input type="hidden" name="tavolo" value="<%=conto.getTavolo()%>">
                                <input type="submit" class="btn btn-primary" value="Stampa Conto">
                            </form>
                        </td>
                    </tr>
                    <%}%>
                    
                </tbody>
            </table>
        </div>


        <%@include file="imports.html" %>
        
        <script src="javascript/conti.js">
            
        </script>
    </body>
</html>
