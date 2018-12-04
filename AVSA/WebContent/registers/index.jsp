<%@page import="java.util.ArrayList"%>
<%@page import="entity.Registro"%>
<% ArrayList<Registro> registros = (ArrayList<Registro>) request.getAttribute("registros"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Mis Registros</title>

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
	            <h1 class="h2">Mis Registros</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	              	<a href="../registros/alta" class="btn btn-sm btn-outline-secondary">Nuevo Registro</a>
	              </div>
	            </div>
	        </div>
	        
	         <div class="table-responsive">
	           <table class="table table-striped table-sm">
	              <thead>
	                <tr class="active">
	                  <th>Fecha</th>
	                  <th>Cuenta</th>
	                  <th>Categoria</th>
	                  <th>Importe</th>
	                  <th>Estado</th>
	                  <th colspan="2" class="text-center">Acciones</th>
	                </tr>
	              </thead>
	              <tbody>
	              <% for(Registro r : registros){ %>
	                <tr>
	                  	<td><%= r.getFechaHora().toLocaleString() %></td>
	                  	<td><%= r.getCuenta().getNombre() %></td>
	                  	<td><%= r.getCategoria().getNombre() %></td>
	                  	<td><%= r.getImporte() %></td>
	                  	<td><%= r.getEstado() %></td>
	                  	<td class="text-center">
							<form method="post" action="../registros/modificacion">
								<input type="hidden" name="id" value="<%=r.getId() %>">
								<button type="submit" class="btn btn-sm btn-primary"><span data-feather="edit"></span></button>
							</form>
		                </td>
	                  	
	                  	<td class="text-center">
							<form name="post_<%=r.getId()%>" style="display:none;" method="post" action="../registros/baja">
								<input type="hidden" name="id" value="<%=r.getId()%>">
							</form>
							<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar el registro')) { document.post_<%=r.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
				      	</td>
	                </tr>
	                <% } %>
	              </tbody>
	            </table>
	        </div>
        </main>
     </div>
    </div>
	
	<jsp:include page="/elementos/footer.jsp"></jsp:include>
    
  </body>
</html>
