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
 * Servlet implementation class ReportesRegistros
 */
@WebServlet({ "/ReportesRegistros", "/reportes/registros", "/Reportes/Registros", "/reportesregistros" })
public class ReportesRegistros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportesRegistros() {
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
		try {
			//String[] filtro = new String[4];
			//Map<String, String> filtro = new HashMap<String, String>();
			Registro filtro = new Registro();
			
			if(request.getParameter("fdesde") != null && request.getParameter("fdesde") != "") {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("fdesde") + " 00:00");
				
				filtro.setFdesde(new Timestamp(fechaHora.getTime()));
				//filtro.put("fdesde", fechaHora.getTime());
			}
			if(request.getParameter("fhasta") != null && request.getParameter("fhasta") != "") {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("fhasta") + " 23:59");
				
				filtro.setFhasta(new Timestamp(fechaHora.getTime()));
			}
			if(request.getParameter("estado") != null && request.getParameter("estado") != "") {
				filtro.setEstado(request.getParameter("estado"));
			}
			if(request.getParameter("tipo") != null && request.getParameter("tipo") != "") {
				filtro.setTipo(request.getParameter("tipo"));
			}
			
			
			CtrlABMRegistro ctrlRegistros = new CtrlABMRegistro();
			ArrayList<Registro> registros;
			registros = ctrlRegistros.reporte(this.user, filtro);
		
			request.setAttribute("registros", registros);
	
			request.getRequestDispatcher("/reports/registros.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			Registro filtro = new Registro();
			
			if(!request.getParameter("fdesde").equals("null") && !request.getParameter("fdesde").isEmpty() ) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("fdesde") + " 00:00");
				
				filtro.setFdesde(new Timestamp(fechaHora.getTime()));
			}
			if(!request.getParameter("fhasta").equals("null") && !request.getParameter("fhasta").isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date fechaHora = dateFormat.parse(request.getParameter("fhasta") + " 23:59");
				
				filtro.setFhasta(new Timestamp(fechaHora.getTime()));
			}
			if(!request.getParameter("estado").equals("null") && !request.getParameter("estado").isEmpty()) {
				filtro.setEstado(request.getParameter("estado"));
			}
			if(!request.getParameter("tipo").equals("null") && !request.getParameter("tipo").isEmpty()) {
				filtro.setTipo(request.getParameter("tipo"));
			}
			
			CtrlABMRegistro ctrlRegistros = new CtrlABMRegistro();
			ArrayList<Registro> registros;
		
			registros = ctrlRegistros.reporte(this.user, filtro);
		
	
			if (registros!= null) {
		        response.setContentType("text/csv");
		        response.setHeader("Content-Disposition", "attachment; filename=\"datos.csv\"");
		        if (!request.isSecure()) {
		            response.setHeader("Cache-Control", "no-cache");
		            response.setHeader("Pragma", "no-cache");
		            response.setHeader("Expires", "0");
		        }
		        PrintWriter writer;
		        writer = response.getWriter();
		        writer.println("Fecha;Cuenta;Categoria;Importe;Tipo;Estado;"); // Encabezado
		        for (Registro d : registros) {
		            //CSVUtils.writeLine(writer, filtro.generarLista(d));
		        	writer.println(filtro.generarLista(d));
		        }
		        writer.flush();
		        writer.close();
		    }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			doGet(request, response);
		} finally {
			
		}
	}

}
