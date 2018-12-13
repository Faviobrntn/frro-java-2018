<%! String url = "/AVerSiAhorra/"; %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Dashboard - Home</title>

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
        	<jsp:include page="elementos/mensajes.jsp"></jsp:include>
        	
        	
        	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      				<h1 class="h2">Dashboard</h1>
      				<div class="btn-toolbar mb-2 mb-md-0"></div>
      			</div>
        	
				<div class="jumbotron">
					<h1 class="display-4">Bienvenido!</h1>
					<p class="lead">A ver si ahorra le ayuda a controlar sus gastos y asi tomarse esas vacaciones bien merecidas.</p>
					<hr class="my-4">
					<p>Comience cargando las <a href="<%= url %>cuentas/" role="button">cuentas</a> que utliza a diario y luego solo <a href="<%= url %>registros/" role="button">registre</a> sus movimientos</p>
					<a class="btn btn-primary btn-lg" href="<%= url %>cuentas/alta" role="button">Comenzar!</a>
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
