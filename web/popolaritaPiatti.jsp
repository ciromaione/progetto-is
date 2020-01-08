<%-- 

    Document   : popolaritaPiatti
    Created on : 8-gen-2020, 20.27.19
    Author     : Alice Vidoni
--%>
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
<div style = "margin-left:34%; margin-right:34%">
	<h2> Seleziona il mese per cui visualizzare i piatti piú popolari</h2>
	<div class="input-group">
		<input class="date form-control" id="date" type="text" placeholder="M/d/y" title="format: M/d/y" aria-autocomplete="none" style="min-width: 7em;">
			<a class="datepicker-button bootstrap3 input-group-addon btn default" role="button" aria-haspopup="true" tabindex="0" aria-labelledby="datepicker-bn-open-label-date">	
			<span class="glyphicon glyphicon-calendar" title="Select date ..."></span>
			</a>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#date').datepicker({
			});
		});
	</script>
</div>
<div>
	<div style="border: groove; width: 32%; margin-left: 34%; margin-top: 2%;">
		<!-- vanno ordinati dal piú popolare al meno popolare tramite js -->
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Nome Piatto</th>
		      <th scope="col">Quantitá</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row">1</th>
		      <td>Gina la gallina</td>
		      <td>122</td>
		    </tr>
		    <tr>
		      <th scope="row">2</th>
		      <td>Hello Panda</td>
		      <td>3</td>
		    </tr>
		  </tbody>
		</table>
	</div>
</div>