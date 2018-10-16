package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Usuarios
 */
@WebServlet({ "/usuarios/*", "/Usuarios/*", "/USUARIOS/*" })
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		httpResponse.setDateHeader("Expires", 0); // Proxies.
		response.getWriter("ABM de UUsauri");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("ABM de Usuarios");
		System.out.println(request.getParameter("id"));
		if(request.getParameter("accion") != null) {
			System.out.print("Esta es un accion");
		}
		request.setAttribute("variable", "valor del parametro");
		switch (request.getPathInfo()) {
			case "/agregar":
				System.out.print("metodo agregar");
				request.getRequestDispatcher("/usuarios/agregar.jsp").forward(request, response);
				break;
				
			case "/baja":
				this.baja(request,response);
				break;
				
			case "/modificacion":
				this.modificacion(request,response);
				break;
	
			default:
				request.getRequestDispatcher("/usuarios/index.jsp").forward(request, response);
				this.error(request,response);
				break;
		}
		
		//request.getRequestDispatcher("/usuarios/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()){
			System.out.println(entry.getKey()+" - "+entry.getValue()[0]);
		}
		
		System.out.print(request.getPathInfo());
		//System.out.println(request.getParameter(""));
		if(request.getParameter("alta") != null) {
			System.out.println("Esta es un a");
			System.out.println("La puta maaaadreeeee!!! ");
			this.agregar(request, response);
		}
		if(request.getParameter("baja") != null) {
			System.out.print("Esta es una Baja");
		}
		if(request.getParameter("edit") != null) {
			System.out.print("Esta es una Modificación");
		}
		if(request.getParameter("search") != null) {
			System.out.print("Esta es una Busqueda");
		}
		request.getRequestDispatcher("/usuarios/index.jsp").forward(request, response);
	}
	
	
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.getRequestDispatcher("/usuarios/agregar.jsp").forward(request, response);
	}
}
