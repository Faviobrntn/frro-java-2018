<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Usuarios - agregar</title>

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
	            <h1 class="h2">Nuevo Usuario</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="/redUTN/usuarios/index.jsp" class="btn btn-sm btn-outline-secondary">Volver</a>
	              </div>
	            </div>
	        </div>
	        <div class="row">    
			    <div class="col-md-8">
			        <h4 class="mb-3">Complete el formulario</h4>
			        <form action="agregar" method="post" class="needs-validation" novalidate="">
			            <div class="row">
			                <div class="col-md-6 mb-3">
			                    <label for="firstName">Nombre</label>
			                    <input type="text" name="nombre" class="form-control" id="firstName" placeholder="" required="required">
			                    <div class="invalid-feedback">
			                        Valid first name is required.
			                    </div>
			                </div>
			                <div class="col-md-6 mb-3">
			                    <label for="lastName">Apellido</label>
			                    <input type="text" name="apellido" class="form-control" id="lastName" placeholder="" required="required">
			                    <div class="invalid-feedback">
			                        Valid last name is required.
			                    </div>
			                </div>
			            </div>
			
			            <div class="mb-3">
			                <label for="email">Email <span class="text-muted">(No es Optional)</span></label>
			                <input type="email" name="email" class="form-control" id="email" placeholder="you@example.com" required="required">
			                <div class="invalid-feedback">
			                    Please enter a valid email address for shipping updates.
			                </div>
			            </div>
			
			            <div class="mb-3">
			                <label for="password">Contraseña</label>
			                <input type="password" name="password" class="form-control" id="password" placeholder="Contraseña" required="required">
			                <div class="invalid-feedback">
			                    Please enter your shipping password.
			                </div>
			            </div>

				        <hr class="mb-4">
				        <button class="btn btn-primary btn-lg btn-block" type="submit">Guardar</button>
			    	</form>
				</div>
			</div>
        </main>
     </div>
   </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery.min.css"></script>
    <script>window.jQuery || document.write('<script src="./jquery-slim.min.js"><\/script>')</script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.css"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
    
    
  </body>
</html>
