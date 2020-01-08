<%-- 

    Document   : popolaritaPiatti
    Created on : 8-gen-2020, 14.59.32
    Author     : Alice Vidoni
--%>

<%@page import="model.managers.TitolareManager"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>

        <% List<TitolareManager.PiattoXQuantita> piatti = (List<TitolareManager.PiattoXQuantita>) request.getAttribute("piatti"); %> 

        <div class="container" style="padding: 40px">
            <h2> Seleziona il mese per cui visualizzare i piatti piú popolari</h2>	
            <form action="popolaritaportate" method="GET">
                <div class="form-group row">
                    <label for="month" class="col-1 col-form-label" style="text-align: right">Mese</label>
                    <div class="col-4">
                        <select id="month" name='mese' class="custom-select">
                            <option value='01'>Gennaio</option>
                            <option value='02'>Febbraio</option>
                            <option value='03'>Marzo</option>
                            <option value='04'>Aprile</option>
                            <option value='05'>Maggio</option>
                            <option value='06'>Giugno</option>
                            <option value='07'>Luglio</option>
                            <option value='08'>Agosto</option>
                            <option value='09'>Settembre</option>
                            <option value='10'>Ottobre</option>
                            <option value='11'>Novembre</option>
                            <option value='12'>Dicembre</option>
                        </select>
                    </div>
                    <label for="year" class="col-1 col-form-label" style="text-align: right">Anno</label>
                    <div class="col-4">
                        <select id="year" name='anno' class="custom-select">
                            <option value='2019'>2019</option>
                            <option value='2020'>2020</option>
                        </select>
                    </div>

                    <div class="col-2">
                        <input type="submit" value="Conferma" class="btn btn-primary">
                    </div>
                </div>

            </form>

            <div>
                <div style="border: groove;margin-top: 20px;width: 60%;margin-left: auto;margin-right: auto">

                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nome Piatto</th>
                                <th scope="col">Quantitá</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int i = 1;
                                for (TitolareManager.PiattoXQuantita piatto : piatti) {%>
                            <tr>
                                <th scope="row"> <%=i++%></th>
                                <td><%=piatto.getPiatto().getNome()%></td>
                                <td><%=piatto.getQuantita()%></td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="imports.html" %>

    </body>  
</html>