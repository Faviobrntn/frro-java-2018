<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cuenta"%>
<% ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Mis Cuentas</title>

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
	            <h1 class="h2">Mis Cuentas</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	              <div class="btn-group mr-2">
	              	<a href="../cuentas/alta" class="btn btn-sm btn-outline-secondary">Nueva cuenta</a>
	              </div>
	            </div>
	        </div>
			
			<div class="row">	        
	        <% for(Cuenta c : cuentas){ %>
	        	<div class="col-sm-12 col-md-4 col-lg-3">
	        		<form name="post_<%=c.getId() %>" style="display:none;" method="post" action="../cuentas/modificacion">
						<input type="hidden" name="id" value="<%=c.getId() %>">
					</form>
					<a href="#" onclick="document.post_<%=c.getId() %>.submit(); event.returnValue = false; return false;">
			        	<div class="card mb-4 shadow-sm" style="background-color: <%= c.getColor() %>;color: white;border-radius: 10px;">
		                	<div class="card-body">
		                  		<h4 class="mb-3"><%= c.getNombre() %></h4>
					            <p class="card-text">Saldo: $ <%= c.getValorInicial() %></p>
		                	</div>
		              	</div>
	              	</a>
	        	</div>
	        	<% } %>
	        </div> 
        </main>
     </div>
    </div>

    <jsp:include page="/elementos/footer.jsp"></jsp:include>
    
  </body>
</html>
