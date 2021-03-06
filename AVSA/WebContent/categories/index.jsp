<%@page import="java.util.ArrayList"%>
<%@page import="entity.Categoria"%>
<% ArrayList<Categoria> category = (ArrayList<Categoria>) request.getAttribute("categorias"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Categorias</title>

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
	            <h1 class="h2">Listado de Categorias</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2"></div>
	            </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-sm-12 col-md-4 col-lg-3">
		        	<div class="card mb-4 shadow-sm">
	                	<div class="card-body">
	                  		<h4 class="mb-3">Agregar Categoria</h4>
					        <form action="../categorias/alta" method="post" class="needs-validation">
				                <div class="form-group">
				                    <label for="nombre">Nombre</label>
				                    <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" required="required" autocomplete="off">
				                    <div class="invalid-feedback">
				                        El nombre es obligatorio.
				                    </div>
				                </div>
				                <div class="form-group">
				                    <label for="descripcion">Descripción</label>
				                    <textarea rows="3" cols="1" name="descripcion" class="form-control" id="descripcion" placeholder="Descripción.."></textarea>
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
			                  <th>Descripción</th>
			                  <th colspan="2" class="text-center">Acciones</th>
			                </tr>
			              </thead>
			              
			              <tbody>
			              	<%
			              		
								for(Categoria c : category){
							%>
			              	<tr>
			                  	<td><%=c.getId() %></td>
			                  	<td><%=c.getNombre() %></td>
								<td><%=c.getDescripcion() %></td>
			                  	<td>
									<form method="post" action="../categorias/modificacion">
										<input type="hidden" name="id" value="<%=c.getId() %>">
										<button type="submit" class="btn btn-sm btn-primary"><span data-feather="edit"></span></button>
									</form>
				                </td>
			                  	
			                  	<td>
									<form name="post_<%=c.getId() %>" style="display:none;" method="post" action="../categorias/baja">
										<input type="hidden" name="id" value="<%=c.getId() %>">
									</form>
									<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar la categoria?')) { document.post_<%=c.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
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
