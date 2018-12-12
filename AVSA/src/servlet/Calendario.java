package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMRegistro;
import entity.Registro;
import entity.Usuario;
import util.Componentes;

/**
 * Servlet implementation class Calendario
 */
@WebServlet({"/Calendario", "/calendario", "/CALENDARIO", "/Calendario/", "/calendario/", "/CALENDARIO/"})
public class Calendario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!Componentes.estaLogeado(request, response)) { throw new Exception("No es un usuario activo. Por favor, vuelva a ingresar."); }
			this.user = (Usuario) request.getSession().getAttribute("usuario");
			
			String method = request.getMethod();
			
		    if (method.equals("GET")) {
		    	doGet(request, response);
		    } else if (method.equals("POST")) {
		        doPost(request, response);
		    }

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			response.sendRedirect("../login");
		}
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("/registers/calendario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			Registro filtro = new Registro();
			
			if(!request.getParameter("start").equals("null") && !request.getParameter("start").isEmpty() ) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("start") + " 00:00");
				
				filtro.setFdesde(new Timestamp(fechaHora.getTime()));
			}
			if(!request.getParameter("end").equals("null") && !request.getParameter("end").isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("end") + " 23:59");
				
				filtro.setFhasta(new Timestamp(fechaHora.getTime()));
			}
			
			
			CtrlABMRegistro ctrlRegistros = new CtrlABMRegistro();
			ArrayList<Registro> registros;
			registros = ctrlRegistros.reporte(this.user, filtro);
			
			String respuesta = "[";
			int contador = 1;
			for(Registro r : registros){
				respuesta += filtro.generarJson(r);
				//respuesta += "{\"id\":\""+r.getId()+"\", \"title\": \""+r.getTipo()+"\", \"start\": \""+r.getFechaHora() +"\", \"end\": \""+r.getFechaHora() +"\"},";
				if(contador < registros.size()) { respuesta += ","; }
				contador++;
			}
			
			respuesta += "]";
			response.setContentType("application/json");
			response.getWriter().println(respuesta);
			
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
		}
	}

}
