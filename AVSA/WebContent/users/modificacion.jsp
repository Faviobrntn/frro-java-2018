<%@page import="java.util.ArrayList, entity.Usuario, entity.Pais"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<% ArrayList<Pais> paises = (ArrayList<Pais>) request.getAttribute("paises"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Usuarios - Editar</title>

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
        <jsp:include page="/elementos/mensajes.jsp"></jsp:include>
        
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	            <h1 class="h2">Editar Usuario</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="../usuarios/" class="btn btn-sm btn-outline-secondary">Volver</a>
	              </div>
	            </div>
	        </div>
	        <div class="row">    
			    <div class="col-md-8">
			        <h4 class="mb-3">Complete el formulario</h4>
			        <form action="../usuarios/modificar" method="post" class="needs-validation" novalidate>
			        	<input type="hidden" name="id" value="<%=usuario.getId()%>">
			            <div class="row">
			                <div class="col-md-6 mb-3">
			                    <label for="firstName">Nombre</label>
			                    <input type="text" name="nombre" class="form-control" id="firstName" placeholder="Nombre" required="required" value="<%=usuario.getNombre()%>">
			                    <div class="invalid-feedback">
			                        Valid first name is required.
			                    </div>
			                </div>
			                <div class="col-md-6 mb-3">
			                    <label for="lastName">Apellido</label>
			                    <input type="text" name="apellido" class="form-control" id="lastName" placeholder="Apellido" required="required" value="<%=usuario.getApellido()%>">
			                    <div class="invalid-feedback">
			                        Valid last name is required.
			                    </div>
			                </div>
			            </div>
			
			            <div class="mb-3">
			                <label for="email">Email <span class="text-muted">(No es Optional)</span></label>
			                <input type="email" name="email" class="form-control" id="email" placeholder="E-mail" required="required" value="<%=usuario.getEmail()%>">
			                <div class="invalid-feedback">
			                    Please enter a valid email address for shipping updates.
			                </div>
			            </div>
			            
			            <div class="form-group">
		                    <label for="pais_id">Pais</label>
		                    <select name="id_pais" class="form-control" id="pais_id" required="required">
		                    	<option value="">Seleccione pais</option>
		                    	<% for(Pais p : paises){ %>
		                    		<% if(usuario.getPais().getId() == p.getId()){ %>
		                    			<option value="<%= p.getId() %>" selected><%= p.getNombre() %></option>
		                    		<% }else{ %>
		                    			<option value="<%= p.getId() %>"><%= p.getNombre() %></option>
		                    		<% } %>
								<% } %>
		                    </select>
		                </div>
		                
			            <div class="form-group">
		                    <label for="rol">Rol</label>
		                    <select name="rol" class="form-control" id="rol" required="required">
		                    	<option value="">Seleccione el rol</option>
		                    	<% if(usuario.getRol().equals("administrador")){ %>
		                    		<option value="administrador" selected>Administrador</option>
		                    		<option value="usuario">Usuario</option>
		                    	<% }else if(usuario.getRol().equals("usuario")){ %>
		                    		<option value="administrador">Administrador</option>
		                    		<option value="usuario" selected>Usuario</option>
	                    		<% }else{ %>
									<option value="administrador">Administrador</option>
		                    		<option value="usuario">Usuario</option>
	                    		<% } %>
		                    	
		                    </select>
		                </div>
	
				        <hr class="mb-4">
				        <button class="btn btn-primary btn-lg btn-block" type="submit" name="accion" value="alta">Guardar</button>
			    	</form>
				</div>
			</div>
        </main>
     </div>
   </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="./jquery-slim.min.js"><\/script>')</script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
    
    
  </body>
</html>
