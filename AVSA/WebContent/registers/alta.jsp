<%@page import="java.util.ArrayList, entity.Categoria, entity.Cuenta, entity.Registro"%>
<% ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas"); %>
<% ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias"); %>
<% String[] tipos = Registro.tipos; %>
<% String[] estados = Registro.estados; %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Registros - agregar</title>

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
	            <h1 class="h2">Nuevo Registro</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="../registros/" class="btn btn-sm btn-outline-secondary">Volver</a>
	              </div>
	            </div>
	        </div>
	        <div class="row">    
			    <div class="col-md-8">
			        <h4 class="mb-3">Complete el formulario</h4>
			        <form action="../registros/alta" method="post" class="needs-validation">
			        	<div class="row">
				            <div class="col-md-4 mb-3">
			                    <label for="tipo">Tipo</label>
			                    <select name="tipo" class="form-control" id="tipo" required="required">
			                    	<% for (int i = 0; i < tipos.length; i++) { %>
			                    	<option value="<%= tipos[i] %>"><%= tipos[i] %></option>
			                    	<% } %>
			                    </select>
			                </div>
		                
				            <div class="col-md-4 mb-3">
			                    <label for="cuenta-id">Cuentas</label>
			                    <select name="cuenta_id" class="form-control" id="cuenta-id" required="required">
			                    	<option value="">Seleccione Cuenta</option>
			                    	<% for(Cuenta c : cuentas){ %>
			                    		<option value="<%= c.getId() %>"><%= c.getNombre() %></option>
									<% } %>
			                    </select>
			                </div>
			                
			                <div class="col-md-4 mb-3">
			                    <label for="categoria-id">Categorias</label>
			                    <select name="categoria_id" class="form-control" id="categoria-id" required="required">
			                    	<option value="">Seleccione categoria</option>
			                    	<% for(Categoria c : categorias){ %>
			                    		<option value="<%= c.getId() %>"><%= c.getNombre() %></option>
									<% } %>
			                    </select>
			                </div>
			                
						</div>
						
			            <div class="row">
			                <div class="col-md-4 mb-3">
			                    <label for="estado">Estado</label>
			                    <select name="estado" class="form-control" id="estado" required="required">
			                    	<option value="">Seleccione el estado</option>
			                    	<% for (int i = 0; i < estados.length; i++) { %>
			                    	<option value="<%= estados[i] %>"><%= estados[i] %></option>
			                    	<% } %>
			                    </select>
			                </div>
			                <div class="col-md-4 mb-3">
			                    <label for="valor-inicial">Importe</label>
			                    <input type="number" name="valor_inicial" class="form-control" id="valor-inicial" required="required">
			                </div>
			            </div>
						
						
						<div class="form-group">
							<label for="notas">Notas</label>
			                <textarea name="notas" class="form-control" id="notas" placeholder="Descripcion.."></textarea>
			            </div>

				        <hr class="mb-4">
				        <button class="btn btn-primary btn-lg btn-block" type="submit" name="accion" value="alta">Guardar</button>
			    	</form>
				</div>
			</div>
        </main>
     </div>
   </div>

    <jsp:include page="/elementos/footer.jsp"></jsp:include>
    
    
  </body>
</html>
