<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% if(request.getSession().getAttribute("mensaje") != null){ %>
 	<div style="top: 50px;text-align: center;position: absolute;width: 100%;z-index: 9999;" onclick="this.style='display:none;'">
	<div class="alert alert-primary" role="alert">
	    <h5><b>Mensaje del sistema</b></h5>
		  <%= request.getSession().getAttribute("mensaje") %>
		</div>
	</div>
	
	<% request.getSession().setAttribute("mensaje", null); %>
<% } %>