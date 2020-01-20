<%-- 

    Document   : ordini
    Created on : 4-gen-2020, 18.19.04
    Author     : ciro
--%>
<%@page import="model.entities.PiattoEffettivo"%>
<%@page import="model.entities.OrdineStaff"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="head.html" %>
    
    <body>
        <%Collection<OrdineStaff> ordini = (Collection<OrdineStaff>) request.getAttribute("ordini");%>
        
        <h2>Ordini rimasti <span id="numero-ordini"><%=ordini.size()%></span></h2>

        <div class="container-fluid">
            <div class="row row-cols-3" id="contenitore-ordini">
                
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
                            for(PiattoEffettivo piatto:ordine.getPiatti()) {
                                String aggiunte = "";
                                String rimozioni = "";
                                for(String a:piatto.getAggiunte()) aggiunte += a+", ";
                                for(String r:piatto.getRimozioni()) rimozioni += r+", ";
                                if(aggiunte.length() >= 2)
                                    aggiunte = aggiunte.substring(0, aggiunte.length()-2);
                                if(rimozioni.length() >= 2)
                                    rimozioni = rimozioni.substring(0, rimozioni.length()-2);
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
                    <form action="rimuoviordine" method="GET">
                        <input type="hidden" name="id" value="<%=ordine.getId()%>">
                        <input type="submit" class="btn btn-success" value="Completato">
                    </form>
                </div>
                    
                <%}%>
            </div>
            
        </div>
        
        
        
        <%@include file="imports.html" %>
        
        <script src="javascript/ordini.js">
            
        </script>
        
    </body>
  
</html>
