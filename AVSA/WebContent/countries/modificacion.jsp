<%@page import="java.util.ArrayList"%>
<%@page import="entity.Pais"%>
<% Pais pais = (Pais) request.getAttribute("pais"); %>
<%@page import="entity.Moneda"%>
<% ArrayList<Moneda> monedas = (ArrayList<Moneda>) request.getAttribute("monedas"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Paises - Modificar</title>

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
	            <h1 class="h2">Editar Paises</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="../paises/" class="btn btn-sm btn-outline-secondary">Volver</a>
	              </div>
	            </div>
	        </div>
	        <div class="row">    
			    <div class="col-md-8">
			        <h4 class="mb-3">Complete el formulario</h4>
			        <form action="../paises/modificar" method="post" class="needs-validation">
			        	<input type="hidden" name="id" value="<%=pais.getId()%>">
		                <div class="form-group">
		                    <label for="nombre">Nombre</label>
		                    <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" required="required" autocomplete="off" value="<%=pais.getNombre()%>">
		                    <div class="invalid-feedback">
		                        El nombre es obligatorio.
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label for="moneda_id">Moneda</label>
		                    <select name="id_moneda" class="form-control" id="moneda_id" required="required">
		                    	<option value="">Seleccione moneda</option>
		                    	<% for(Moneda m : monedas){ %>
		                    		<% if(pais.getMoneda().getId() == m.getId()){ %>
		                    		<option value="<%= m.getId() %>" selected><%= m.getNombre() %></option>
		                    		<% }else{ %>
		                    		<option value="<%= m.getId() %>"><%= m.getNombre() %></option>
		                    		<% } %>
								<% } %>
		                    </select>
		                </div>
				        <hr class="mb-4">
				        <button class="btn btn-primary btn-lg btn-block" type="submit" name="accion" value="modificar">Guardar</button>
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
