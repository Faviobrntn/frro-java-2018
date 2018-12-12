<%! String url = "/AVerSiAhorra/"; %>
<%@page import="entity.Usuario"%>
<% 
Usuario user = new Usuario();
if(request.getSession().getAttribute("usuario") != null){ 
	user = (Usuario) request.getSession().getAttribute("usuario");
} 
%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <!-- <a class="nav-link active" href="LinkToPage?url=home.jsp"> -->
                <!--<a class="nav-link active" href="< %= url %>home">-->
                <a class="nav-link active" href="../home/">
                    <span data-feather="home"></span>
                    Dashboard <span class="sr-only">(current)</span>
                </a>
            </li>
            <% if(user.getRol().equals("administrador")){ %>
            <li class="nav-item">
                <!--<a class="nav-link" href="< %= url %>usuarios/"> -->
                <a class="nav-link" href="../usuarios/">
                    <span data-feather="users"></span>
                    Usuarios
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>categorias/">
                    <span data-feather="archive"></span>
                    Categorias
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>paises/">
                    <span data-feather="database"></span>
                    Paises
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>monedas/">
                    <span data-feather="dollar-sign"></span>
                    Monedas
                </a>
            </li>
            <% } %>
            
            
            <% if(user.getRol().equals("usuario")){ %>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>cuentas/">
                    <span data-feather="tag"></span>
                    Mis Cuentas
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>registros/">
                    <span data-feather="layers"></span>
                    Mis Registros
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>calendario/">
                    <span data-feather="layers"></span>
                    Calendario
                </a>
            </li>
            <% } %>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span>Reportes</span>
            <a class="d-flex align-items-center text-muted" href="#">
                <span data-feather="plus-circle"></span>
            </a>
        </h6>
        <ul class="nav flex-column mb-2">
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>reportes/registros">
                    <span data-feather="bar-chart-2"></span>
                    Registros
                </a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="<%= url %>reportes/estadisticas-anuales">
                    <span data-feather="bar-chart-2"></span>
                    Estadisticas Anuales
                </a>
            </li>
           <!-- <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Year-end sale
                </a>
            </li> -->
        </ul>
    </div>
</nav>