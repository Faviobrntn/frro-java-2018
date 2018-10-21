package servlet;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Usuarios
 */
@WebServlet({ "/usuarios/*", "/Usuarios/*", "/USUARIOS/*", "/usuarios" })
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
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		System.out.println(request.getPathInfo());
		
		//request.setAttribute("variable", "valor del parametro");
		if(request.getPathInfo() == null) {
			request.getRequestDispatcher("/users/index.jsp").forward(request, response);
		}else {
			switch (request.getPathInfo()) {
				case "/alta":
					System.out.println("metodo agregar");
					request.getRequestDispatcher("/users/alta.jsp").forward(request, response);
					break;
					
				case "/baja":
					response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through get");
					break;
					
				case "/modificacion":
					response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
					break;
				
				case "/":
					request.getRequestDispatcher("/users/index.jsp").forward(request, response);
					break;
					
				default:
					request.getRequestDispatcher("/users/index.jsp").forward(request, response);
					break;
			}
		}
		//request.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("ENTRO POR POST");
		System.out.println(request.getPathInfo());
		System.out.println(request.getParameter("alta"));
		//System.out.println(request.getParameter(""));
		if(request.getParameter("alta") != null || request.getPathInfo() == "alta") {
			guardar(request, response);
		}
		if(request.getParameter("baja") != null) {
			System.out.println("Esta es una Baja");
			baja(request, response);
		}
		if(request.getParameter("modificacion") != null) {
			System.out.println("Esta es una Modificación");
			modificacion(request, response);
		}
		
		request.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}
	
	
	protected void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()){
			System.out.println(entry.getKey()+" - "+entry.getValue()[0]);
		}
		if(request.getParameter("id") != null) {
			System.out.println("Es una MODIFICACIOOOOON");
		}else {
			System.out.println("Es un ALTA");
		}
	}
	
	protected void modificacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("MODIFICACIOOOOON");
		System.out.println(request.getPathInfo());
		System.out.println(request.getParameter("id"));
		request.getRequestDispatcher("/users/modificacion.jsp").forward(request, response);
	}
	
	protected void baja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("DELETEEEEEE");
		System.out.println(request.getPathInfo());
		System.out.println(request.getParameter("id"));
		request.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}
	
}
