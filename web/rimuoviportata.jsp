<%-- 
    Document   : rimuoviportata
    Created on : 8-gen-2020, 19.46.08
    Author     : ciro
--%>

<<<<<<< HEAD
<%@page import="model.entities.Piatto"%>
<%@page import="java.util.Collection"%>
=======
>>>>>>> 2df7f220f309a234903c23985284df7588765e5a
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.html" %>
    <body>
<<<<<<< HEAD
    <%Collection<Piatto> piatti = (Collection<Piatto>) request.getAttribute("piatti");%>
    <div class="container">
=======

        <div class="container">
>>>>>>> 2df7f220f309a234903c23985284df7588765e5a
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
<<<<<<< HEAD
                    <% for(Piatto piatto : piatti) { %>
                    <tr>
                        <th scope="row"><%=piatto.getNome() %></th>
                        <td><%=piatto.getCategoria() %></td>
                        <td> € <%=piatto.getPrezzoString() %></td>
                        <td>
                            <form action="rimuovi" method="GET">
                                <input type="hidden" name="id-piatto" value="id"> <%=piatto.getId() %>
=======
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
>>>>>>> 2df7f220f309a234903c23985284df7588765e5a
                                <input type="submit" value="Rimuovi" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
<<<<<<< HEAD
                    <% } %>
                    
=======
>>>>>>> 2df7f220f309a234903c23985284df7588765e5a
                </tbody>
            </table>
        </div>



        <%@include file="imports.html" %>
    </body>
</html>
