<%-- 
    Document   : aggiungiportata
    Created on : 8-gen-2020, 22.27.45
    Author     : ciro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="head.html" %>
    
    <body>
        
        <div class="container">
            <h2 class="title">Aggiungi Piatto</h2>
            
            <form action="aggiungi" method="POST" style="max-width: 700px; margin-left: auto; margin-right: auto;">
                
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
                            <option value="categoria1">Categoria 1</option>
                            <option value="categoria2">Categoria 2</option>
                            <option value="categoria3">Categoria 3</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="prezzo" class="col-3 col-form-label">Prezzo Piatto</label>
                    <div class="col-9">
                        <input type="text" class="form-control" name="prezzo" id="prezzo">
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
                            <option value="categoria1">Categoria 1</option>
                            <option value="categoria2">Categoria 2</option>
                            <option value="categoria3">Categoria 3</option>
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
                            <input type="checkbox" class="form-check-input" id="aggiungibile">
                            <label class="form-check-label" for="aggiungibile">Aggiungibile</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="rimovibile">
                            <label class="form-check-label" for="rimovibile">Rimovibile</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-3"></div>
                    <div class="col-9">
                        <button class="btn btn-success">Aggiungi</button>
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
                        <textarea class="form-control" id="txtfissi" rows="4"></textarea>
                    </div>
                    <div class="col-4">
                        <textarea class="form-control" id="txtagg" rows="4"></textarea>
                    </div>
                    <div class="col-4">
                        <textarea class="form-control" id="txtrem" rows="4"></textarea>
                    </div>
                </div>
                
                <hr>
                
                <input type="submit" value="Conferma" class="btn btn-success">
                
            </form>
        </div>

        
        <%@include file="imports.html" %>
        
    </body>
</html>
