<%-- 
    Document   : areariservata
    Created on : 4-gen-2020, 18.06.43
    Author     : ciro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menú Maxi</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
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
                <button class="buttonSize" onclick="location.href = 'ricaviodierni'" >Ricavi Giornalieri</button>
                <button class="buttonSize" onclick="location.href = 'aggiungiportata'">Aggiungi Portata</button>
                <button class="buttonSize" onclick="location.href = 'rimuoviportata'">Rimuovi Portata</button>
                <button class="buttonSize" onclick="location.href = 'popolaritaodierne'">Popolaritá Piatti</button>
            </div>
        </div>
    </body>
</html>
