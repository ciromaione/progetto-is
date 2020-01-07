<%-- 

    Document   : ordini
    Created on : 4-gen-2020, 18.19.04
    Author     : ciro
--%>
<%@page import="model.entities.PiattoStaff"%>
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
                            <%
                            for(PiattoStaff piatto:ordine.getPiatti()) {
                                String aggiunte = "";
                                String rimozioni = "";
                                for(String a:piatto.getAggiunte()) aggiunte += a+", ";
                                for(String r:piatto.getRimozioni()) rimozioni += r+", ";
                                aggiunte = aggiunte.substring(0, aggiunte.length()-1);
                                rimozioni = rimozioni.substring(0, rimozioni.length()-1);
                            %>
                            <tr>
                                <th scope="row"><%=piatto.getNomePiatto()%></th>
                                <td><%=piatto.getQuantita()%></td>
                                <td><%=aggiunte%></td>
                                <td><%=rimozioni%></td>
                            </tr>
                            <%}%>
                            
                        </tbody>
                    </table>	
                    <button type="button" class="btn btn-success">Completato</button>
                </div>
            </div>
                
            <%}%>
        </div>
        
        
        
        <%@include file="imports.html" %>
        
        <script>
            $(document).ready(function () {
                
                
                
            });
        </script>
        
    </body>
  
</html>
