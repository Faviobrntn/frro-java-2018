<%! String url = "/AVerSiAhorra/"; String nombre = "AVSA"; String perfil = "#"; %>
<%@page import="entity.Usuario"%>
<% 
if(request.getSession().getAttribute("usuario") != null){ 
	Usuario u = (Usuario) request.getSession().getAttribute("usuario");
	nombre = u.getNombre();
	perfil = url+"perfil/";
} 
%>

<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
   <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="<%= perfil %>"><%= nombre %></a>
   <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
   <ul class="navbar-nav px-3">
     <li class="nav-item text-nowrap">
       <a class="nav-link" href="<%= url %>perfil/cerrar-sesion" onclick="return confirm('¿Seguro que desea cerrar sesion?');">Salir</a>
     </li>
   </ul>
</nav>
 
<% if(request.getAttribute("mensajeFlash") != null){ %>
 <div style="top: 0;text-align: center;position: absolute;width: 100%;">
	<div class="alert alert-primary" role="alert">
    	<h5><b>Mensaje del sistema</b></h5>
	  	<%= request.getAttribute("mensajeFlash")  %>
	</div>
</div>
<% } %>