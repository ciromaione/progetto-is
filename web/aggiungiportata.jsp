<%-- 
    Document   : aggiungiportata
    Created on : 8-gen-2020, 22.27.45
    Author     : ciro
--%>

<%@page import="model.entities.Ingrediente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="head.html" %>
    
    <body>
        <%List<String> categorie = (List<String>) request.getAttribute("categorie");
        List<Ingrediente> ingredienti = (List<Ingrediente>) request.getAttribute("ingredienti");%>
        <div class="container">
            <h2 class="title">Aggiungi Piatto</h2>
            
            <form action="aggiungi" method="POST" id="form" style="max-width: 700px; margin-left: auto; margin-right: auto;">
                
                <div class="form-group row">
                    <label for="nome" class="col-3 col-form-label">Nome Piatto</label>
                    <div class="col-9">
                        <input type="text" class="form-control" name="nome" id="nome">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="categoria" class="col-3 col-form-label">Categoria Piatto</label>
                    <div class="col-9">
                        <select class="form-control" id="categoria" name="categoria">
                            <% for(String categoria: categorie) { %>
                            <option value="categoria1"><%=categoria %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="prezzo" class="col-3 col-form-label">Prezzo Piatto</label>
                    <div class="col-9">
                        <input type="text" class="form-control" name="prezzo" id="prezzo" placeholder="0,00">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="immagine" class="col-3 col-form-label">Immagine Piatto</label>
                    <div class="col-9">
                        <input type="file" class="form-control-file" id="immagine" aria-describedby="fileHelp" name="immagine">
                    </div>
                </div>
                
                <hr>
                <div class="form-group row">
                    <label for="ingredienti" class="col-3 col-form-label">Ingredienti</label>
                    <div class="col-7">
                        <select class="form-control" id="ingredienti">
                            <% for(Ingrediente i: ingredienti) { %>
                            <option value="<%=i.getId()%>"><%=i.getNome() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-2">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#aggiungi-ingrediente">Nuovo</button>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <div class="form-check">
                            <input type="radio" class="form-check-input" name="tipo-ing" value="0" checked>
                            <label class="form-check-label">Fisso</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <div class="form-check">
                            <input type="radio" class="form-check-input" name="tipo-ing" value="1">
                            <label class="form-check-label">Aggiungibile</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <div class="form-check">
                            <input type="radio" class="form-check-input" name="tipo-ing" value="2">
                            <label class="form-check-label">Rimovibile</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <a class="btn btn-success" id="aggiungi-btn" style="text-decoration: none;color: white;">Aggiungi</a>
                    </div>
                </div>
                <div class="form-group row">
                    
                </div>
                <div class="form-group row">
                    <div class="col-4">
                        <label>Ingredienti fissi</label>
                    </div>
                    <div class="col-4">
                        <label>Ingredienti aggiungibili</label>
                    </div>
                    <div class="col-4">
                        <label>Ingredienti rimovibili</label>
                    </div>
                    <div class="col-4">
                        <textarea class="form-control" id="txtfissi" rows="4" readonly></textarea>
                    </div>
                    <div class="col-4">
                        <textarea class="form-control" id="txtagg" rows="4" readonly></textarea>
                    </div>
                    <div class="col-4">
                        <textarea class="form-control" id="txtrim" rows="4" readonly></textarea>
                    </div>
                </div>
                
                <hr>
                <input type="hidden" id="ing-fissi" name="ing-fissi">
                <input type="hidden" id="ing-agg" name="ing-agg">
                <input type="hidden" id="ing-rim" name="ing-rim">
                <a class="btn btn-success" id="submit-btn" style="text-decoration: none; color: white">Conferma</a>
                
            </form>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="aggiungi-ingrediente" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Aggiungi Ingrediente</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group row">
                            <label for="nome ing" class="col-2 col-form-label">Nome</label>
                            <div class="col-10">
                                <input type="text" class="form-control" name="nome-ing" id="nome-ing">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="cat-ing" class="col-2 col-form-label">Categoria</label>
                            <div class="col-10">
                                <select class="form-control" id="cat-ing">
                                    <option value="carne">Carne</option>
                                    <option value="contorno">Contorno</option>
                                    <option value="salsa">Salsa</option>
                                    <option value="formaggio">Formaggio</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="sovrapprezzo" class="col-3 col-form-label">Sovrapprezzo</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="sovrapprezzo" id="sovrapprezzo" placeholder="0,00">
                            </div>
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" id="salva-ing">Salva</button>
                    </div>
                </div>
            </div>
        </div>

        
        <%@include file="imports.html" %>
        <script src="javascript/aggiungiportata.js"></script>
        <%if(request.getAttribute("conferma") != null) {%>
        <script>
            $(document).ready(() => {
                alert("Piatto aggiunto con successo!");
            });
        </script>
        <%}%>
    </body>
</html>