<%-- 
    Document   : areariservata
    Created on : 4-gen-2020, 18.06.43
    Author     : ciro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="head.html" %>
    
    <body>		
        <div class="mainMenuContainer">
            <div class="buttonContanitor">	
                <button class="buttonSize" onclick="location.href = 'ricavigiiornalieri'" >Ricavi Giornalieri</button>
                <button class="buttonSize" onclick="location.href = 'aggiungiportata'">Aggiungi Portata</button>
                <button class="buttonSize" onclick="location.href = 'rimuoviportate'">Rimuovi Portata</button>
                <button class="buttonSize" onclick="location.href = 'popolaritaportate'">Popolaritá Piatti</button>
            </div>
        </div>
        
        <%@include file="imports.html" %>
    </body>
</html>
