<%-- 

    Document   : ordini
    Created on : 4-gen-2020, 18.19.04
    Author     : ciro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div class="mainMenuContainer">
	<div class="box">
		<h2 style="margin-left:25%">ORDINE TAVOLO 1</h2>
		<div style="border: groove; width: 60%; height: 33%; margin-left: 20%; margin-top: 2%;">
			<div style="margin-left:10%; height:100%">
				<b>Piatto 1</b>
				<p style="margin-left:27%; margin-top:-8%">x2</p>
				<b>Aggiunta:</b>
				<p style="margin-left:27%; margin-top:-8%">panino</p>
				<b>Rimozione:</b>
				<p style="margin-left:29%; margin-top:-8%">cipolla</p>
			</div>
		</div>
		<div style="border: groove; width: 60%; height: 33%; margin-left: 20%; margin-top: 2%;">
			<div style="margin-left:10%; height:100%">
				<b>Piatto 2</b>
				<p style="margin-left:27%; margin-top:-8%">x1</p>
				<b>Aggiunta:</b>
				<p style="margin-left:27%; margin-top:-8%">maionese</p>
				<b>Rimozione:</b>
				<p style="margin-left:29%; margin-top:-8%">insalata,pomodoro,ketchup,hamburgher,pollo,cane,gatto</p>
			</div>
		</div>
		<button type="button" class="btn btn-success" style="margin-left:40%; margin-top:7.7%">Completato</button>
	</div>
	<div style="margin-left:20%" class="box">
		<button class="bigButton">RICHIESTA DI CONTO</button>
	</div>
</div>

<%@include file="footer.jsp"%>
