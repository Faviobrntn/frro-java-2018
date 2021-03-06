<!doctype html>
<html lang="en">
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
    
    <script type="text/javascript">
    	//function (res)
    </script>
  </head>

  <body class="text-center">
    <form class="form-signin" action="addUsuario" method="post">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Ingrese los datos</h1>
      <label for="inputEmail" class="sr-only">Nombre</label>
      <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre" required autofocus>
      <label for="inputEmail" class="sr-only">Apellido</label>
      <input type="text" name="apellido" id="apellido" class="form-control" placeholder="Apellido" required autofocus>
      <label for="inputEmail" class="sr-only">Email</label>
      <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
      
      
      <button class="btn btn-lg btn-primary btn-block" type="submit" name="alta">Alta</button>
      <button class="btn btn-lg btn-danger btn-block" type="submit" name="baja">Baja</button>
      <button class="btn btn-lg btn-success btn-block" type="submit" name="edit">Modificación</button>
      <button class="btn btn-lg btn-info btn-block" type="submit" name="search">Buscar</button>
       
      <!-- 
      <button class="btn btn-lg btn-primary btn-block" onclick="javascript: enviarForm('alta')">Alta</button>
      <button class="btn btn-lg btn-danger btn-block" onclick="javascript: enviarForm('baja')">Baja</button>
      <button class="btn btn-lg btn-success btn-block" onclick="javascript: enviarForm('edit')">Modificación</button>
      <button class="btn btn-lg btn-info btn-block" onclick="javascript: enviarForm('search')">Buscar</button>
      -->
    </form>
  </body>
</html>
