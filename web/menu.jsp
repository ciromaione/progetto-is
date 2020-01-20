<%-- 
    Document   : menu
    Created on : 20-gen-2020, 10.08.05
    Author     : ciro
--%>

<%@page import="model.entities.Menu"%>
<%@page import="model.entities.Ingrediente"%>
<%@page import="model.entities.Piatto"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>
        <div class="container-fluid">

            <ul class="nav nav-tabs" role="tablist" id="myTab">
                <%
                    Menu menu = (Menu) request.getAttribute("menu");
                    Collection<String> categorie = menu.getMenu().keySet();
                    boolean active = true;
                    for (String cat : categorie) {

                %>
                <li class="nav-item">
                    <a class="nav-link <%if (active) {%>active<%}%>" id="<%=cat%>-tab" data-toggle="tab" href="#<%=cat%>" role="tab" aria-controls="<%=cat%>" aria-selected="<%=active%>"><%=cat.toUpperCase()%></a>
                </li>

                <%
                        active = false;
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" id="riepilogo-tab" data-toggle="tab" href="#riepilogo" role="tab" aria-controls="riepilogo" aria-selected="false">Il Tuo Ordine</a>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">

                <div class="tab-pane fade" id="riepilogo" role="tabpanel" aria-labelledby="riepilogo-tab">
                    <div>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Quantitá</th>
                                    <th scope="col">Prezzo</th>
                                </tr>
                            </thead>
                            <tbody id="tbody">

                            </tbody>
                        </table>

                    </div>
                    <h4>Totale = <span id="totaleordine"></span> €</h4>
                    <button class="btn btn-success" id="confermaordinebtn">Conferma</button>
                </div>

                <%
                    active = true;
                    for (String categoria : categorie) {
                        Collection<Piatto> piatti = menu.getMenu().get(categoria);

                %>
                <div class="tab-pane fade <%if (active) {%>show active<%}%>" id="<%=categoria%>" role="tabpanel" aria-labelledby="<%=categoria%>-tab">  

                    <%active = false;%>

                    <h2 style="text-align: center"><%=categoria.toUpperCase()%></h2>

                    <div class="row row-cols-3">

                        <%
                            for (Piatto p : piatti) {
                                String ingredienti = "";
                                for (Ingrediente ing : p.getIngredienti()) {
                                    ingredienti += ing.getNome() + ", ";
                                }
                                for (Ingrediente ing : p.getIngredientiRimovibili()) {
                                    ingredienti += ing.getNome() + ", ";
                                }
                                if (ingredienti.length() >= 2)
                                    ingredienti = ingredienti.substring(0, ingredienti.length() - 2);
                        %>

                        <div class="col" style="padding: 20px;">
                            <div class="card" style="width: 18rem;">
                                <img class="card-img-top" src="img/<%=p.getFoto()%>" alt="piatto">
                                <div class="card-body">
                                    <h4 class="card-title"><%=p.getNome()%></h4>
                                    <h5>Ingredienti:</h5>
                                    <p class="card-text"><%=ingredienti%></p>
                                    <h5>Prezzo <%=p.getPrezzoString()%> €</h5>
                                    <hr>
                                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#modifica-piatto-<%=p.getId()%>">Aggiungi</a>

                                </div>
                            </div> 
                        </div>

                        <%}%>

                    </div>


                    <%
                        for (Piatto p : piatti) {
                            Collection<Ingrediente> agg = p.getIngredientiAggiungibili();
                            Collection<Ingrediente> rim = p.getIngredientiRimovibili();
                            if (agg != null || rim != null) {
                    %>
                    <!-- Modal -->
                    <div class="modal fade" id="modifica-piatto-<%=p.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="exampleModalLongTitle">Modifica Piatto</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <%if (agg != null) {%>
                                    <h5>Ingredienti Aggiungibili</h5>
                                    <%for (Ingrediente i : agg) {%>
                                    <div class="form-group">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="<%=i.getId()%>" name="check-add-<%=p.getId()%>">
                                            <input type="hidden" id="nomeing-<%=i.getId()%>" value="<%=i.getNome()%>">
                                            <input type="hidden" id="sovrapprezzo-<%=i.getId()%>" value="<%=i.getSovrapprezzoCent()%>">
                                            <label class="form-check-label" for="invalidCheck">
                                                <%=i.getNome() + "   " + i.getSovrapprezzoString() + "€"%>
                                            </label>
                                        </div>
                                    </div>
                                    <%}%>
                                    <hr>
                                    <%
                                        }
                                        if (rim != null) {
                                    %>
                                    <h5>Ingredienti Rimovibili</h5>
                                    <%for (Ingrediente i : rim) {%>
                                    <div class="form-group">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="<%=i.getId()%>" name="check-rem-<%=p.getId()%>">
                                            <input type="hidden" id="nomeing-<%=i.getId()%>" value="<%=i.getNome()%>">
                                            <label class="form-check-label" for="invalidCheck">
                                                <%=i.getNome()%>
                                            </label>
                                        </div>
                                    </div>
                                    <%}
                                    }%>
                                    <hr>
                                    <div class="form-group row">
                                        <div class="col-2"></div>
                                        <label class="col-4 col-form-label">Quantità</label>
                                        <div class="col-4">
                                            <select class="form-control" style="width: 60px" id="select-<%=p.getId()%>">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" onclick="aggiungiPiattoAOrdine(<%=p.getId()%>, '<%=p.getNome()%>', <%=p.getPrezzoCent()%>)">Aggiungi</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                    }%>

                </div>  
                <%}%>

            </div>
        </div>
        <%@include file="imports.html" %>

        <script src="javascript/inviaPiatto.js"></script>
    </body>
</html>
