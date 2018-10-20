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
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		System.out.println(request.getPathInfo());
		
		//request.setAttribute("variable", "valor del parametro");
		if(request.getPathInfo() == null) {
			request.getRequestDispatcher("/users/index.jsp").forward(request, response);
		}
		switch (request.getPathInfo()) {
			case "/agregar":
				System.out.print("metodo agregar");
				request.getRequestDispatcher("/users/agregar.jsp").forward(request, response);
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
		//request.getRequestDispatcher("/users/index.jsp").forward(request, response);
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
		System.out.println("HOLAAAAAAAAAAA");
		System.out.print(request.getPathInfo());
		//System.out.println(request.getParameter(""));
		if(request.getParameter("agregar") != null) {
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
		
		request.getRequestDispatcher("/usuarios/index.jsp").forward(request, response);
	}
	
	
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.getRequestDispatcher("/usuarios/agregar.jsp").forward(request, response);
	}
}
