<%-- 

    Document   : ordini
    Created on : 4-gen-2020, 18.19.04
    Author     : ciro
--%>
<%@page import="model.entities.OrdineStaff"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="head.html" %>
    
    <body>
        <%Collection<OrdineStaff> ordini = (Collection<OrdineStaff>) request.getAttribute("ordini");%>
        
        <h2>Ordini rimasti <%=ordini.size()%></h2>

        <div class="container-fluid">
            <div class="row row-cols-3">
                
                <%
                for(OrdineStaff ordine:ordini) {
                %>

                <div class="col">
                    
                    <h2>Ordine Tavolo <%=ordine.getTavolo()%></h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">Quantitá</th>
                                <th scope="col">Aggiunta</th>
                                <th scope="col">Rimosso</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Arancino</th>
                                <td>2</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <th scope="row">Big Jin</th>
                                <td>1</td>
                                <td>patatine, insalata, mayo</td>
                                <td>cipolle, ketchup</td>
                            </tr>
                            <tr>
                                <th scope="row">Chesee cake</th>
                                <td>6</td>
                                <td>panna</td>
                                <td>fragole</td>
                            </tr>
                        </tbody>
                    </table>	
                    <button type="button" class="btn btn-success">Completato</button>
                </div>
            </div>
                
            <%}%>
        </div>
        
        
        
        <%@include file="imports.html" %>
        
    </body>
  
</html>
