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

        <div style = "margin-left:34%; margin-right:34%">
            <h2> Seleziona la data di cui vuoi visualizzare i ricavi</h2>
            <form action="ricavigiornalieri" method="POST">
                <div class="input-group">
                    <input class="date form-control" name="date" id="date" type="text" placeholder="M/d/y" title="format: M/d/y" aria-autocomplete="none" style="min-width: 7em;">
                    <a class="datepicker-button bootstrap3 input-group-addon btn default" role="button" aria-haspopup="true" tabindex="0" aria-labelledby="datepicker-bn-open-label-date">	
                        <span class="glyphicon glyphicon-calendar" title="Select date ..."></span>
                    </a>
                </div>
            </form>
        </div>
        <div>
            <div style="border: groove; width: 32%; margin-left: 34%; margin-top: 2%;">
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
                    <th scope="row"></th>
                    <td></td>
                    <td></td>
                    <td>Somma dei ricavi: <%=getPrezzoString(totale)%></td>
                    </tr>
                    </tbody>
                </table>
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