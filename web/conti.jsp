<%-- 
    Document   : conti
    Created on : 7-gen-2020, 22.57.30
    Author     : ciro
--%>

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
                        <th scope="col"># Tavolo</th>
                        <th scope="col">Totale Spesa</th>
                        <th scope="col">Metodo di Pagamento</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">Tavolo 1</th>
                        <td>100euro</td>
                        <td>Carta di credito</td>
                        <td><button type="button" class="btn btn-success">Paga</button></td>
                    </tr>
                    <tr>
                        <th scope="row">Tavolo 2</th>
                        <td>75,50 euro</td>
                        <td>Contanti</td>
                        <td><button type="button" class="btn btn-success">Paga</button></td>
                    </tr>
                    <tr>
                        <th scope="row">Tavolo 3</th>
                        <td>25euro</td>
                        <td>Contanti</td>
                        <td><button type="button" class="btn btn-success">Paga</button></td>
                    </tr>
                    <tr>
                        <th scope="row">Tavolo 4</th>
                        <td>382euro</td>
                        <td>Carta di Debito</td>
                        <td><button type="button" class="btn btn-success">Paga</button></td>
                    </tr>
                </tbody>
            </table>
        </div>


        <%@include file="imports.html" %>
    </body>
</html>
