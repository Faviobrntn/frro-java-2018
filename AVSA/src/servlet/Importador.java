package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import util.Componentes;

/**
 * Servlet implementation class Importador
 */
@WebServlet({"/Importador", "/importador", "/reportes/importador", "/Reportes/Importador"})
public class Importador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Importador() {
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
		if(request.getParameter("ejemplo") != null && request.getParameter("ejemplo").equals("true")) {
			response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment; filename=\"plantilla_ejemplo.csv\"");
	        if (!request.isSecure()) {
	            response.setHeader("Cache-Control", "no-cache");
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Expires", "0");
	        }
	        PrintWriter writer;
	        writer = response.getWriter();
	        writer.println("Fecha;Cuenta;Categoria;Importe;Tipo;Estado;"); // Encabezado
	        writer.println("04/12/2018 20:00;Santander;Ingreso;1000;Ingresos;Conciliado;");
	        writer.flush();
	        writer.close();
	        
	        response.sendRedirect("../reportes/importador");
		}else {
			request.getRequestDispatcher("/reports/importador.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
