<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Usuarios</title>

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
	            <h1 class="h2">Listado de Usuarios</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	                <a href="../usuarios/alta" class="btn btn-sm btn-outline-secondary">Nuevo usuario</a>
	                <!--<button class="btn btn-sm btn-outline-secondary">Export</button>-->
	              </div>
	            </div>
	        </div>
	        <div class="table-responsive">
	           <table class="table table-striped table-sm">
	              <thead>
	                <tr class="active">
	                  <th>#</th>
	                  <th>Header</th>
	                  <th>Header</th>
	                  <th>Header</th>
	                  <th>Header</th>
	                  <th colspan="2">Acciones</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td>1,001</td>
	                  <td>Lorem</td>
	                  <td>ipsum</td>
	                  <td>dolor</td>
	                  <td>sit</td>
	                  <td><a href="../usuarios/modificacion" class="btn btn-sm btn-primary"><span data-feather="edit"></span></a></td>
	                  <td><a href="../usuarios/baja" class="btn btn-sm btn-danger"><span data-feather="trash"></span></a></td>
	                </tr>
	                <tr>
	                  <td>1,002</td>
	                  <td>amet</td>
	                  <td>consectetur</td>
	                  <td>adipiscing</td>
	                  <td>elit</td>
	                  <td><a href="../usuarios/modificacion" class="btn btn-sm btn-primary"><span data-feather="edit"></span></a></td>
	                  <td><a href="../usuarios/baja" class="btn btn-sm btn-danger"><span data-feather="trash"></span></a></td>
	                </tr>
	                <tr>
	                  <td>1,003</td>
	                  <td>Integer</td>
	                  <td>nec</td>
	                  <td>odio</td>
	                  <td>Praesent</td>
	                  <td>
							<form method="post" action="../usuarios/modificacion">
								<input type="hidden" name="id" value="123">
								<button type="submit" class="btn btn-sm btn-primary"><span data-feather="edit"></span></button>
							</form>
	                  </td>
	                  <td>
							<form name="post_5bcbc147ef7a6374751506" style="display:none;" method="post" action="../usuarios/baja">
								<input type="hidden" name="id" value="123">
							</form>
							<a href="#" class="btn btn-sm btn-danger" onclick="if (confirm('¿Seguro que desea eliminar el usuario?')) { document.post_5bcbc147ef7a6374751506.submit(); } event.returnValue = false; return false;"><span data-feather="trash"></span></a>
				      </td>
	                </tr>
	              </tbody>
	            </table>
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

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
  </body>
</html>
