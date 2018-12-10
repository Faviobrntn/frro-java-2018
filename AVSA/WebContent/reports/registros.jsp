<%@page import="java.util.ArrayList, entity.Registro"%>
<%@page import="entity.Registro"%>
<% ArrayList<Registro> registros = (ArrayList<Registro>) request.getAttribute("registros"); %>
<% String[] tipos = Registro.tipos; %>
<% String[] estados = Registro.estados; %>
<% String colorIngreso = "#abe6ab"; String colorGasto = "#eaafaf"; %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Reportes | Registros</title>

    <!-- Bootstrap core CSS -->
    <link href="../styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../styles/dashboard.css" rel="stylesheet">
    
    <link href="../styles/bootstrap-datetimepicker.min.css" rel="stylesheet">
  </head>

  <body>
    <jsp:include page="/elementos/header.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="/elementos/menu.jsp"></jsp:include>
        
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	            <h1 class="h2">Reportes de registros</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	            	<form method="post">
						<input type="hidden" name="fdesde" value="<%= request.getParameter("fdesde") %>">
						<input type="hidden" name="fhasta" value="<%= request.getParameter("fhasta") %>">
						<input type="hidden" name="estado" value="<%= request.getParameter("estado") %>">
						<input type="hidden" name="tipo" value="<%= request.getParameter("tipo") %>">
						<button type="submit" class="btn btn-sm btn-outline-secondary"><span data-feather="download"></span> Descargar CSV</button>
					</form>
	            </div>
	        </div>
	        <div class="row">
	        	<div class="col-sm-12 col-md-12 col-lg-12">
	        		<div class="card mb-4 shadow-sm">
	                	<div class="card-body">
	                		<h5 class="card-title">Filtro</h5>
				            <p class="card-text"></p>
				            
				            <form method="get" autocomplete="off">
								  <div class="form-row">
								    <div class="form-group col-md-2">
								      <label for="fecha-desde">Fecha desde</label>
								      <input type="text" name="fdesde" class="form-control fecha" id="fecha-desde">
								    </div>
								    <div class="form-group col-md-2">
								      <label for="fecha-hasta">Fecha hasta</label>
								      <input type="text" name="fhasta" class="form-control fecha" id="fecha-hasta">
								    </div>
								    <div class="form-group col-md-4">
									    <label for="estados">Estados</label>
									    <select name="estado" class="form-control" id="estados">
					                    	<option value=""></option>
					                    	<% for (int i = 0; i < estados.length; i++) { %>
					                    	<option value="<%= estados[i] %>"><%= estados[i] %></option>
					                    	<% } %>
					                    </select>
									  </div>
									  <div class="form-group col-md-4">
									      	<label for="tipos">Tipo de registro</label>
									      	<select name="tipo" class="form-control" id="tipos">
									      		<option value=""></option>
						                    	<% for (int i = 0; i < tipos.length; i++) { %>
						                    	<option value="<%= tipos[i] %>"><%= tipos[i] %></option>
						                    	<% } %>
					                    	</select>
									   </div>
								  </div>
								  
								  <div style="height:30px;"></div>
								  <button type="submit" class="btn btn-primary float-right">Filtrar</button>
								  <button type="reset" class="btn btn-default">Limpiar</button>
							</form>
	                	</div>
	              	</div>
	        	</div>
	        	
	        	        
	        	<div class="col-sm-12 col-md-12 col-lg-12">
			         <div class="table-responsive">
			           <table class="table table-hover table-sm">
			              <thead>
			                <tr class="active">
			                  <th>Fecha</th>
			                  <th>Cuenta</th>
			                  <th>Categoria</th>
			                  <th>Importe</th>
			                  <th>Tipo</th>
			                  <th>Estado</th>
			                </tr>
			              </thead>
			              <tbody>
			              <% for(Registro r : registros){ %>
			              	<% if(r.getTipo().equals("Ingresos")){ %>
			                <tr style="background-color: <%= colorIngreso %>">
			                <% }else if(r.getTipo().equals("Gastos")){ %>
			                <tr style="background-color: <%= colorGasto %>">
			                <% }else{ %>
			                <tr>
			                <% } %>
			                  	<td><%= r.getFechaHora().toLocaleString() %></td>
			                  	<td><%= r.getCuenta().getNombre() %></td>
			                  	<td><%= r.getCategoria().getNombre() %></td>
			                  	<td><%= r.getImporte() %></td>
			                  	<td><%= r.getTipo() %></td>
			                  	<td><%= r.getEstado() %></td>
			                </tr>
			                <% } %>
			              </tbody>
			            </table>
			        </div>
			    </div>
			</div>
        </main>
     </div>
    </div>
	
	<jsp:include page="/elementos/footer.jsp"></jsp:include>
    
  </body>
</html>
