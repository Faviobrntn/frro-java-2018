<%@page import="java.util.ArrayList, entity.Registro"%>
<% String[] tipos = Registro.tipos; %>
<% String[] estados = Registro.estados; %>
<% String colorIngreso = "#abe6ab"; String colorGasto = "#eaafaf"; %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Reportes | Importador</title>

    <!-- Bootstrap core CSS -->
    <link href="../styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../styles/dashboard.css" rel="stylesheet">
    
    <link href="../styles/bootstrap-datetimepicker.min.css" rel="stylesheet">
  </head>

  <body>
    <jsp:include page="/elementos/header.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="/elementos/menu.jsp"></jsp:include>
        
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	            <h1 class="h2">Importador de registros</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	            	<form method="get">
						<button type="submit" class="btn btn-sm btn-outline-secondary" name="ejemplo" value="true"><span data-feather="download"></span> Descargar CSV</button>
					</form>
	            </div>
	        </div>
	        <div class="row">
	        	<div class="col-sm-12 col-md-12 col-lg-12">
	        		<div class="card mb-4 shadow-sm">
	                	<div class="card-body">
	                		<h5 class="card-title">Importar Registros</h5>
				            <p class="card-text"></p>
				            
				            <form method="post" autocomplete="off" enctype="multipart/form-data">
							    <div class="form-group col-md-12">
							      <label for="file">Archivo</label>
							      <input type="file" id="file" name="archivo" accept="text/csv" class="form-control">
							    </div>
								  
								  <div style="height:30px;"></div>
								  <button type="submit" class="btn btn-primary float-right">Importar!</button>
								  <button type="reset" class="btn btn-default">Limpiar</button>
							</form>
	                	</div>
	              	</div>
	        	</div>

			</div>
        </main>
     </div>
    </div>
	
	<jsp:include page="/elementos/footer.jsp"></jsp:include>
    
  </body>
</html>
