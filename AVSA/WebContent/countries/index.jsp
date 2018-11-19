<%@page import="java.util.ArrayList, entity.Pais, entity.Moneda"%>
<% ArrayList<Pais> country = (ArrayList<Pais>) request.getAttribute("paises"); %>
<% ArrayList<Moneda> monedas = (ArrayList<Moneda>) request.getAttribute("monedas"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Paises</title>

    <!-- Bootstrap core CSS -->
    <link href="../styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../styles/dashboard.css" rel="stylesheet">
  </head>

  <body>
    <jsp:include page="/elementos/header.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="/elementos/menu.jsp"></jsp:include>
        
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	            <h1 class="h2">Listado de Paises</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2"></div>
	            </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-sm-12 col-md-4 col-lg-3">
		        	<div class="card mb-4 shadow-sm">
	                	<div class="card-body">
	                  		<h4 class="mb-3">Agregar Pais</h4>
					        <form action="../paises/alta" method="post" class="needs-validation">
				                <div class="form-group">
				                    <label for="nombre">Nombre</label>
				                    <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" required="required" autocomplete="off">
				                    <div class="invalid-feedback">
				                        El nombre es obligatorio.
				                    </div>
				                </div>
				                <div class="form-group">
				                    <label for="moneda_id">Moneda</label>
				                    <select name="id_moneda" class="form-control" id="moneda_id" required="required">
				                    	<option value="">Seleccione moneda</option>
				                    	<% for(Moneda m : monedas){ %>
				                    		<option value="<%= m.getId() %>"><%= m.getNombre() %></option>
										<% } %>
				                    </select>
				                </div>
						        <hr class="mb-4">
						        <button class="btn btn-primary btn-lg btn-block" type="submit" name="accion" value="alta">Guardar</button>
					    	</form>
	                	</div>
	              	</div>
	        	</div>
	        	
	        	<div class="col-sm-12 col-md-8 col-lg-9">
	        		<div class="table-responsive">
			           <table class="table table-hover table-sm">
			              <thead>
			                <tr class="active">
			                  <th>#</th>
			                  <th>Nombre</th>
			                  <th>Moneda</th>
			                  <th colspan="2" class="text-center">Acciones</th>
			                </tr>
			              </thead>
			              
			              <tbody>
			              	<%
								for(Pais p : country){
							%>
			              	<tr>
			                  	<td><%=p.getId() %></td>
			                  	<td><%=p.getNombre() %></td>
			                  	<td><%=p.getMoneda().getNombre() %></td>
			                  	<td>
									<form method="post" action="../paises/modificacion">
										<input type="hidden" name="id" value="<%=p.getId() %>">
										<button type="submit" class="btn btn-sm btn-primary"><span data-feather="edit"></span></button>
									</form>
				                </td>
			                  	
			                  	<td>
									<form name="post_<%=p.getId() %>" style="display:none;" method="post" action="../paises/baja">
										<input type="hidden" name="id" value="<%=p.getId() %>">
									</form>
									<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar el pais?')) { document.post_<%=p.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
						      	</td>
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

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../jquery-slim.min.js"><\/script>')</script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
  </body>
</html>