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
            
            <form action="aggiungi" method="POST">
                
                <div class="form-group row">
                    <label for="nome" class="col-2 col-form-label">Nome Piatto</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nome" id="nome">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="categoria" class="col-2 col-form-label">Categoria Piatto</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="categoria" name="categoria">
                            <option value="categoria1">Categoria 1</option>
                            <option value="categoria2">Categoria 2</option>
                            <option value="categoria3">Categoria 3</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="prezzo" class="col-2 col-form-label">Prezzo Piatto</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="prezzo" id="prezzo">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="immagine" class="col-2 col-form-label">Immagine Piatto</label>
                    <div class="col-sm-10">
                        <input type="file" class="form-control-file" id="immagine" aria-describedby="fileHelp" name="immagine">
                    </div>
                </div>
                
            </form>
        </div>

        
        <%@include file="imports.html" %>
        
    </body>
</html>
