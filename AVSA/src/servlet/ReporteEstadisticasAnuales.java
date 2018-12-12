package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class ReporteEstadisticasAnuales
 */
@WebServlet({ "/ReporteEstadisticasAnuales", "/reporte-estadisticas-anuales", "/reportes/estadisticas-anuales", "/reportes/estadisticasAnuales" })
public class ReporteEstadisticasAnuales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteEstadisticasAnuales() {
        super();
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
		try {
			Registro filtro = new Registro();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			Date fechaHora = null;
			
			if(request.getParameter("fdesde") != null && request.getParameter("fdesde") != "") {
				fechaHora = dateFormat.parse(request.getParameter("fdesde") + " 00:00");
				filtro.setFdesde(new Timestamp(fechaHora.getTime()));
			}
			if(request.getParameter("fhasta") != null && request.getParameter("fhasta") != "") {
				fechaHora = dateFormat.parse(request.getParameter("fhasta") + " 23:59");
				filtro.setFhasta(new Timestamp(fechaHora.getTime()));
			}
			
			//Seteo a mano las 2 fechas, hasta que este el filtro
			fechaHora = dateFormat.parse("01/01/2018 00:00");
			filtro.setFdesde(new Timestamp(fechaHora.getTime()));
			fechaHora = dateFormat.parse("31/12/2018 23:59");
			filtro.setFhasta(new Timestamp(fechaHora.getTime()));
			
			CtrlABMRegistro ctrlRegistros = new CtrlABMRegistro();
			ArrayList<Registro> registros;
			registros = ctrlRegistros.reporte(this.user, filtro);
		
			request.setAttribute("registros", registros);
	
			request.getRequestDispatcher("/reports/estadisticas_anuales.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			response.sendRedirect("/home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			doGet(request, response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			doGet(request, response);
		} finally {
			
		}
	}

}
