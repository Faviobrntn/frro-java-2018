<%@page import="java.util.ArrayList, entity.Pais"%>
<% ArrayList<Pais> paises = (ArrayList<Pais>) request.getAttribute("paises"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico"> 
    
    <!-- ../ URL a un nivel mas arriba -->
    <!-- ./ URL relativo -->

    <title>Registro</title>

    <!-- Bootstrap core CSS -->
    <link href="./styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
</head>

<body class="text-center">
		<% if(request.getAttribute("mensajeFlash") != null){ %>
	  	<div style="top: 0;text-align: center;position: absolute;width: 100%;">
			<div class="alert alert-primary" role="alert">
			    <h5><b>Mensaje del sistema</b></h5>
				  <%= request.getAttribute("mensajeFlash")  %>
				</div>
			</div>
		<% } %>
		

      <form class="form-signin" action="registrarse" method="post">
      <h1 class="h3 mb-3 font-weight-normal">Ingreso al sistema</h1>
     
      <input type="text" name="nombre" class="form-control" id="firstName" placeholder="Nombre" required="required">
      
      <input type="text" name="apellido" class="form-control" id="lastName" placeholder="Apellido" required="required">
      
      <input type="email" name="email" class="form-control" id="email" placeholder="E-mail" required="required">

      <input type="password" name="password" class="form-control" id="password" placeholder="Contraseña" required="required">

       <label for="pais_id">Pais</label>
       <select name="id_pais" class="form-control" id="pais_id" required="required">
       	<option value="">Seleccione pais</option>
       	<% for(Pais p : paises){ %>
       		<option value="<%= p.getId() %>"><%= p.getNombre() %></option>
		<% } %>
       </select>         

       <hr class="mb-4">
       <button class="btn btn-success btn-lg btn-block" type="submit">Registrarse!</button>
  	</form>
    
  </body>
</html>
