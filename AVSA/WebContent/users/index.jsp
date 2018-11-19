<%@page import="java.util.ArrayList, entity.Usuario"%>
<% ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Usuarios</title>

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
	            <h1 class="h2">Listado de Usuarios</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="../usuarios/alta" class="btn btn-sm btn-outline-secondary">Nuevo usuario</a>
	                <!--<button class="btn btn-sm btn-outline-secondary">Export</button>-->
	              </div>
	            </div>
	        </div>
	        <div class="table-responsive">
	           <table class="table table-striped table-sm">
	              <thead>
	                <tr class="active">
	                  <th>#</th>
	                  <th>Nombre</th>
	                  <th>Apellido</th>
	                  <th>Email</th>
	                  <th>Pais</th>
	                  <th>Rol</th>
	                  <th colspan="2" class="text-center">Acciones</th>
	                </tr>
	              </thead>
	              <tbody>
	              <% for(Usuario u : usuarios){ %>
	                <tr>
	                  	<td><%= u.getId() %></td>
	                  	<td><%= u.getNombre() %></td>
	                  	<td><%= u.getApellido() %></td>
	                  	<td><%= u.getEmail() %></td>
	                  	<td><%= u.getPais().getNombre() %></td>
	                  	<td><%= u.getRol() %></td>
	                  	<td class="text-center">
							<form method="post" action="../usuarios/modificacion">
								<input type="hidden" name="id" value="<%=u.getId() %>">
								<button type="submit" class="btn btn-sm btn-primary"><span data-feather="edit"></span></button>
							</form>
		                </td>
	                  	
	                  	<td class="text-center">
							<form name="post_<%=u.getId()%>" style="display:none;" method="post" action="../usuarios/baja">
								<input type="hidden" name="id" value="<%=u.getId()%>">
							</form>
							<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar al usuario?')) { document.post_<%=u.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
				      	</td>
	                </tr>
	                <% } %>
	              </tbody>
	            </table>
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
