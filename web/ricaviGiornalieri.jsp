<%-- 

    Document   : ricaviGiornalieri
    Created on : 7-gen-2020, 20.27.19
    Author     : Alice Vidoni
--%>
<%@page import="model.managers.TitolareManager"%>
<%@page import="model.managers.TitolareManager.PiattoXQuantita"%>
<%@page import="java.util.List"%>

<%@include file="header.jsp"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.en.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">

<%!  public String getPrezzoString(int price) {
        String price = Integer.toString(price);
        int size = price.length();
        switch (size) {
            case 1:
                return "0,0" + price;
            case 2:
                return "0," + price;
            default:
                return price.substring(0, size-2)+","+price.substring(size-2);
        }
    } %>

<% List<TitolareManager.PiattoXQuantita> ricavi= (List<TitolareManager.PiattoXQuantita>)request.getAttribute("ricavi"); 
   String guadagno_totale=(String)request.getAttribute("totale");
   int totale=Integer.parseInt(guadagno_totale);
    %> 

<div style = "margin-left:34%; margin-right:34%">
	<h2> Seleziona la data di cui vuoi visualizzare i ricavi</h2>
        <form action="ricavigiornalieri" method="POST">
	<div class="input-group">
		<input class="date form-control" name="date" id="date" type="text" placeholder="M/d/y" title="format: M/d/y" aria-autocomplete="none" style="min-width: 7em;">
			<a class="datepicker-button bootstrap3 input-group-addon btn default" role="button" aria-haspopup="true" tabindex="0" aria-labelledby="datepicker-bn-open-label-date">	
			<span class="glyphicon glyphicon-calendar" title="Select date ..."></span>
			</a>
	</div>
        </form>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#date').datepicker({
			});
		});
	</script>
</div>
<div>
	<div style="border: groove; width: 32%; margin-left: 34%; margin-top: 2%;">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Nome Piatto</th>
                      <th scope="col">Categoria</th>
		      <th scope="col">Quantitá</th>
		      <th scope="col">Ricavo Tot</th>
		    </tr>
		  </thead>
		  <tbody>
                      <% int i=0;
                        for (TitolareManager.PiattoXQuantita ricavo: ricavi) { %>
		    <tr>
		      <th scope="row"><%=i++%></th>
		      <td><%=ricavo.getPiatto().getNome()%></td>
		      <td><%=ricavo.getPiatto().getCategoria()%></td>
                      <td><%=ricavo.getQuantita() %></td>
                      <% int tot=ricavo.getQuantita()*ricavo.getPiatto().getPrezzoCent();%>
                      <td><%=getPrezzoString(tot) %></td>
		    </tr>
		    <% } %>
		      <th scope="row"></th>
		      <td></td>
		      <td></td>
		      <td>Somma dei ricavi: <%=getPrezzoString(totale) %></td>
		    </tr>
		  </tbody>
		</table>
	</div>
</div>

