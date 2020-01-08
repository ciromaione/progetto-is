<%-- 

    Document   : popolaritaPiatti
    Created on : 8-gen-2020, 14.59.32
    Author     : Alice Vidoni
--%>

<%@page import="model.managers.TitolareManager"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.en.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>

<% List<TitolareManager.PiattoXQuantita> piatti= (List<TitolareManager.PiattoXQuantita>)request.getAttribute("piatti"); %> 
    
<div style = "margin-left:34%; margin-right:34%">
	<h2> Seleziona il mese per cui visualizzare i piatti piú popolari</h2>	
	<form action="popolaritaportate" method="post">
	
	<div style="margin-top:7%">
		<div class="dropdown" style="margin-left:33%">
	  Mese:

    <select id="month" name='month'>
        <option value='01'>Gennaio</option>
        <option value='02'>Febbraio</option>
        <option value='03'>Marzo</option>
        <option value='04'>Aprile</option>
        <option value='05'>Maggio</option>
        <option value='06'>Giugno</option>
        <option value='07'>Luglio</option>
        <option value='08'>Agosto</option>
        <option value='09'>Settembre</option>
        <option value='10'>Ottobre</option>
        <option value='11'>Novembre</option>
        <option value='12'>Dicembre</option>
    </select>

	  
	  Anno:
    <select id="year" name='year'>
        <option value='2019'>2019</option>
         <option value='2020'>2020</option>
    </select>
          <input type='button' value='Conferma' onclick="setName();">

        </div>
	</div>
        <input type="hidden" id="mese" name="mese">
        <input type="hidden"id="anno" name="anno">
	</form>
        
        <script language="javascript" type="text/javascript">
            function setName() {
                var mese=document.getElementById("month").value;
                var anno=document.getElementById("year").value;
                document.getElementById("mese").innerHTML=mese;
                document.getElementById("anno").innerHTML=anno;
            }
        </script>
</div>
<div>
	<div style="border: groove; width: 32%; margin-left: 34%; margin-top: 2%;">
		
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Nome Piatto</th>
		      <th scope="col">Quantitá</th>
		    </tr>
		  </thead>
		  <tbody>
                      <% int i=0;
                      for (TitolareManager.PiattoXQuantita piatto: piatti) { %>
		    <tr>
		      <th scope="row"> <%=i++%></th>
		      <td><%=piatto.getPiatto().getNome() %></td>
		      <td><%=piatto.getQuantita() %></td>
		    </tr>
		    <% } %>
		  </tbody>
		</table>
	</div>
</div>