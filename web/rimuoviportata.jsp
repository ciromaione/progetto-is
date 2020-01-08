<%-- 
    Document   : rimuoviportata
    Created on : 8-gen-2020, 19.46.08
    Author     : ciro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>

        <div class="container">
            <h2 style="text-align: center">Rimozione Portate</h2>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Nome Piatto</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Prezzo</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">Insalata</th>
                        <td>Contorno</td>
                        <td>5 €</td>
                        <td>
                            <form action="rimuovi" method="GET">
                                <input type="hidden" name="id-piatto" value="id">
                                <input type="submit" value="Rimuovi" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Insalata</th>
                        <td>Contorno</td>
                        <td>5 €</td>
                        <td>
                            <form action="rimuovi" method="GET">
                                <input type="hidden" name="id-piatto" value="id">
                                <input type="submit" value="Rimuovi" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Insalata</th>
                        <td>Contorno</td>
                        <td>5 €</td>
                        <td>
                            <form action="rimuovi" method="GET">
                                <input type="hidden" name="id-piatto" value="id">
                                <input type="submit" value="Rimuovi" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>



        <%@include file="imports.html" %>
    </body>
</html>
