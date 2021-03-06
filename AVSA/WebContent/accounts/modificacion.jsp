<%@page import="java.util.ArrayList, entity.Moneda, entity.Cuenta"%>
<% ArrayList<Moneda> money = (ArrayList<Moneda>) request.getAttribute("monedas"); %>
<% String[] tipos = Cuenta.tipos; %>
<% Cuenta cuenta = (Cuenta) request.getAttribute("cuenta"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Cuentas - editar</title>

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
	            <h1 class="h2">Editar cuenta</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	            	<div class="btn-group mr-2">
		            	<form name="post_<%=cuenta.getId() %>" style="display:none;" method="post" action="../cuentas/baja">
							<input type="hidden" name="id" value="<%=cuenta.getId() %>">
						</form>
						<a href="#" class="btn btn-sm btn-outline-danger" onclick="if (confirm('¿Seguro que desea eliminar la cuenta? \n(Se perderan todos los registros)')) { document.post_<%=cuenta.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
	              	</div>
	              	<div class="btn-group mr-2">
	                	<a href="../cuentas/" class="btn btn-sm btn-outline-secondary">Volver</a>
	              	</div>
	            </div>
	        </div>
	        <div class="row">    
			    <div class="col-md-8">
			        <h4 class="mb-3">Complete el formulario</h4>
			        <form action="../cuentas/modificar" method="post" class="needs-validation">
			        <input type="hidden" name="id" value="<%=cuenta.getId()%>">
			            <div class="row">
			                <div class="col-md-6 mb-3">
			                    <label for="nombre">Nombre</label>
			                    <input type="text" name="nombre" class="form-control" id="nombre" placeholder="" required="required" value="<%=cuenta.getNombre()%>">
			                </div>
			                <div class="col-md-6 mb-3">
			                    <label for="valor-inicial">Valor inicial</label>
			                    <input type="number" name="valor_inicial" class="form-control" id="valor-inicial" required="required" value="<%=cuenta.getValorInicial()%>">
			                </div>
			            </div>
						
						<div class="row">
				            <div class="col-md-4 mb-3">
			                    <label for="tipo">Tipo</label>
			                    <select name="tipo" class="form-control" id="tipo" required="required">
			                    	<option value="">Seleccione el tipo de cuenta</option>
			                    	<% for (int i = 0; i < tipos.length; i++) { %>
			                    		<% if(cuenta.getTipo().equals(tipos[i])){ %>
			                    			<option value="<%= tipos[i] %>" selected><%= tipos[i] %></option>
			                    		<% }else{ %>
			                    			<option value="<%= tipos[i] %>"><%= tipos[i] %></option>
										<% } %>
			                    	
			                    	<% } %>
			                    </select>
			                </div>
		                
				            <div class="col-md-4 mb-3">
			                    <label for="moneda-id">Moneda</label>
			                    <select name="moneda_id" class="form-control" id="moneda-id" required="required">
			                    	<option value="">Seleccione Moneda</option>
			                    	<% for(Moneda p : money){ %>
			                    		<% if(cuenta.getMoneda().getId() == p.getId()){ %>
		                    				<option value="<%= p.getId() %>" selected><%= p.getNombre() %></option>
			                    		<% }else{ %>
			                    			<option value="<%= p.getId() %>"><%= p.getNombre() %></option>
										<% } %>
									<% } %>
			                    </select>
			                </div>
			                
			                <div class="col-md-4 mb-3">
			                	<label for="color">Color <span class="text-muted">(No es Optional)</span></label>
			                	<input type="color" name="color" class="form-control" id="color" style="height: 60%;" required="required" value="<%=cuenta.getColor()%>">
			            	</div>
						</div>
						<div class="form-group">
							<label for="descripcion">Descripción</label>
			                <textarea name=descripcion class="form-control" id="descripcion" placeholder="Descripcion.."><%=cuenta.getDescripcion()%></textarea>
			            </div>

				        <hr class="mb-4">
				        <button class="btn btn-primary btn-lg btn-block" type="submit" name="accion" value="modificar">Guardar</button>
			    	</form>
				</div>
			</div>
        </main>
     </div>
   </div>

    <jsp:include page="/elementos/footer.jsp"></jsp:include>
    
    
  </body>
</html>
