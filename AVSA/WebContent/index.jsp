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

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="./styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
  	<jsp:include page="elementos/mensajes.jsp"></jsp:include>
	

    <form class="form-signin" action="login" method="post">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Ingreso al sistema</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" name="email" id="inputEmail" class="form-control" placeholder="E-mail" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Contraseņa" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Recordarme
        </label>
      </div>
      <button class="btn btn-lg btn-primary active" type="submit">Iniciar Sesion</button>
      <a href="registrarse" class="btn btn-lg btn-secondary active" role="button" aria-pressed="true" >Registrarse</a>
      <p class="mt-5 mb-3 text-muted">&copy; A ver si ahorra-2018</p>
    </form>
  </body>
</html>
