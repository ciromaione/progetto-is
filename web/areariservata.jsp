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
        <header>
            <!-- Main bar-->
            <div class="menunavContainer" style="height:110px">
                <!-- Space for the logo -->
                <div class="logocontainer">
                    <img style="width:120px; height:110px" alt="" src="Images/logoGnam.png">
                </div>
                <div class="menunav" style = "font-family: Royal">
                    <button class="buttonMain">Logout</button>
                    <button class="buttonMain">Cambia PW</button>
                </div>
            </div>
        </header>			
        <div class="mainMenuContainer">
            <div class="buttonContanitor">	
                <button class="buttonSize" onclick="location.href = 'ricavigiiornalieri'" >Ricavi Giornalieri</button>
                <button class="buttonSize" onclick="location.href = 'aggiungiportata'">Aggiungi Portata</button>
                <button class="buttonSize" onclick="location.href = 'rimuoviportata'">Rimuovi Portata</button>
                <button class="buttonSize" onclick="location.href = 'popolaritaportate'">Popolaritá Piatti</button>
            </div>
        </div>
        
        <%@include file="imports.html" %>
    </body>
</html>
