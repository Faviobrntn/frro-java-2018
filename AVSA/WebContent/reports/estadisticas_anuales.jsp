<%@page import="java.util.ArrayList, java.util.Map, entity.Registro"%>
<% ArrayList<Registro> registros = (ArrayList<Registro>) request.getAttribute("registros"); %>
<% String colorIngreso = "#abe6ab"; String colorGasto = "#eaafaf"; %>
<% Map<String, Float> grafico = (Map<String, Float>) request.getAttribute("grafico"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">

    <title>Reportes - Estadistica Anual</title>

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
            <h1 class="h2">Estadisticas Anuales</h1>
            <div class="btn-toolbar mb-2 mb-md-0">
            </div>
          </div>

          <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>

          <h2>Registros</h2>
          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr class="active">
                  <th>Fecha</th>
                  <th>Cuenta</th>
                  <th>Categoria</th>
                  <th>Importe</th>
                  <th>Tipo</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tbody>
              <% for(Registro r : registros){ %>
                <tr>
                  	<td><%= r.getFechaHora().toLocaleString() %></td>
                  	<td><%= r.getCuenta().getNombre() %></td>
                  	<td><%= r.getCategoria().getNombre() %></td>
                  	<td><%= r.getImporte() %></td>
                  	<td><%= r.getTipo() %></td>
                  	<td><%= r.getEstado() %></td>
                </tr>
                <% } %>
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
    <script>window.jQuery || document.write('<script src="./jquery-slim.min.js"><\/script>')</script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

    <!-- Graphs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script>
    	//var meses = {"1": 0, "2": 0, "3": 0, "4": 0, "5": 0, "6": 0, "7": 0, "8": 0, "9": 0, "10": 0, "11": 0, "12": 0};
    	var meses = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    	<% for (Map.Entry<String, Float> entry : grafico.entrySet()) { %>
		    meses[(<%= entry.getKey() %>) - 1] = <%= entry.getValue() %>;
		<% }  %>
      	var ctx = document.getElementById("myChart");
      	var myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
          datasets: [{
            //data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
            data: meses,
            lineTension: 0,
            backgroundColor: 'transparent',
            borderColor: '#007bff',
            borderWidth: 4,
            pointBackgroundColor: '#007bff'
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: false
              }
            }]
          },
          legend: {
            display: false,
          }
        }
      });
    </script>
  </body>
</html>
