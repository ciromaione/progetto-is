<%-- 

    Document   : ricaviGiornalieri
    Created on : 7-gen-2020, 20.27.19
    Author     : Alice Vidoni
--%>
<%@page import="model.managers.TitolareManager"%>
<%@page import="model.managers.TitolareManager.PiattoXQuantita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@include file="head.html" %>
    <body>
        <%!  public String getPrezzoString(int price) {
                String prezzoString = Integer.toString(price);
                int size = prezzoString.length();
                switch (size) {
                    case 1:
                        return "0,0" + price;
                    case 2:
                        return "0," + price;
                    default:
                        return prezzoString.substring(0, size - 2) + "," + prezzoString.substring(size - 2);
                }
            }%>

        <%
            List<TitolareManager.PiattoXQuantita> ricavi = (List<TitolareManager.PiattoXQuantita>) request.getAttribute("ricavi");
            Integer totale = (Integer) request.getAttribute("totale");
        %> 
        <div class="container" style="padding: 40px">
            <div>
                <form action="ricavigiornalieri" method="GET">
                    <div class="form-group row">
                        <label for="date-input" class="col-2 col-form-label">Data</label>
                        <div class="col-7">
                            <input class="form-control" type="date" id="date-input" name="date">
                        </div>
                        <div class="col-3">
                            <input type="submit" value="Conferma" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </div>
            <div>
                <div style="border: groove; width: 32%; margin-left: 34%; margin-top: 2%;">
                    <h3>Ricavi Totali = <%=getPrezzoString(totale)%> €</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nome Piatto</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Quantitá</th>
                                <th scope="col">Ricavo Tot</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int i = 0;
                            for (TitolareManager.PiattoXQuantita ricavo : ricavi) {%>
                            <tr>
                                <th scope="row"><%=i++%></th>
                                <td><%=ricavo.getPiatto().getNome()%></td>
                                <td><%=ricavo.getPiatto().getCategoria()%></td>
                                <td><%=ricavo.getQuantita()%></td>
                                <% int tot = ricavo.getQuantita() * ricavo.getPiatto().getPrezzoCent();%>
                                <td><%=getPrezzoString(tot)%></td>
                            </tr>
                            <% }%>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="imports.html" %>

        <script>
            $(document).ready(function () {
                $('#date').datepicker({
                });
            });
        </script>
    </body>
</html>