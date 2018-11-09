<%@page import="java.util.ArrayList"%>
<%@page import="entity.Moneda"%>
<% ArrayList<Moneda> coins = (ArrayList<Moneda>) request.getAttribute("monedas"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Monedas</title>

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
	            <h1 class="h2">Listado de Monedas</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2"></div>
	            </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-sm-12 col-md-4 col-lg-3">
		        	<div class="card mb-4 shadow-sm">
	                	<div class="card-body">
	                  		<h4 class="mb-3">Agregar Moneda</h4>
					        <form action="../monedas/alta" method="post" class="needs-validation">
				                <div class="form-group">
				                    <label for="nombre">Nombre</label>
				                    <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" required="required" autocomplete="off">
				                    <div class="invalid-feedback">
				                        El nombre es obligatorio.
				                    </div>
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
			                  <th colspan="2" class="text-center">Acciones</th>
			                </tr>
			              </thead>
			              
			              <tbody>
			              	<%
								for(Moneda coin : coins){
							%>
			              	<tr>
			                  	<td><%=coin.getId() %></td>
			                  	<td><span id="coin-<%=coin.getId() %>"><%=coin.getNombre() %></span></td>
			                  	<td>
									<button type="submit" class="btn btn-sm btn-primary btnModificarMoneda" data-id="<%=coin.getId() %>" data-nombre="<%=coin.getNombre() %>"><span data-feather="edit"></span></button>
				                </td>
			                  	
			                  	<td>
									<form name="post_<%=coin.getId() %>" style="display:none;" method="post" action="../monedas/baja">
										<input type="hidden" name="id" value="<%=coin.getId() %>">
									</form>
									<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar la moneda?')) { document.post_<%=coin.getId() %>.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
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
	
	
	<script>
		$(document).ready(function(){
			$(".btnModificarMoneda").on('click', function(e){
				e.preventDefault();
				var id = $(this).data('id');
				var nombre = $(this).data('nombre');
				var campo = $("#coin-"+id);
				
				var nuevo_nombre = prompt("Ingrese el nuevo nombre", nombre);
				if(nuevo_nombre.trim() != "" && nuevo_nombre.trim() != nombre){
					$.ajax({
			            type:'PUT',
			            url: "../monedas/modificar",
			            data:{ 
			            	id: id,
			            	nombre: nuevo_nombre
			            },
			            dataType: "json",
			            success: (data) => {
			            	console.log(data);
			                if(data.estado){
			                	campo.html(nombre_nuevo);
			                }else{
			                    alert("No se pudo modificar el nombre de la moneda.");
			                    campo.html(nombre);
			                }
			            },
			            error: (data) => {
			                console.error(data);
			            }
			        });
				}
			});
		});
	</script>
	
    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
  </body>
</html>