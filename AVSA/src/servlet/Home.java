package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Componentes;


@WebServlet({ "/home", "/Home", "/HOME", "/home/*"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!Componentes.estaLogeado(request, response)) { throw new Exception("No es un usuario activo. Por favor, vuelva a ingresar."); }
			
			String method = request.getMethod();
			
			if (method.equals("GET")) {
		        doGet(request, response);

		    } else if (method.equals("HEAD")) {
		        doHead(request, response);

		    } else if (method.equals("POST")) {
		        doPost(request, response);

		    } else if (method.equals("PUT")) {
		        doPut(request, response);

		    } else if (method.equals("DELETE")) {
		        doDelete(request, response);

		    } else if (method.equals("OPTIONS")) {
		        doOptions(request, response);

		    } else if (method.equals("TRACE")) {
		        doTrace(request, response);

		    } else {
		    	doGet(request, response);
		        //
		        // Note that this means NO servlet supports whatever
		        // method was requested, anywhere on this server.
		        //
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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Entro al HOME por GET");
		//response.getWriter().append("get");
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request.getParameter("email") --> leo el name del form
		
		// request.getSession()  ---> SESSION: Si no existe la crea
		/* CONDICIONES para guardar un objeto en SESION: 
		 * tiene que ser un JAVA BINS
		 * 		ser serializable (public cass User implements Serializable (){} ) 
		 *  	tenes un construcctor publico y sin parametros
		 *  	getter y setter para todos sus atributos
		 * 
		 * request.getSession().setAttribute("usuario", u);  User u = new User(); 
		 */
		
		/*
		 * request.getRequestDispatcher("WEB-INF/UserManagement.jsp")  
		 * --> sirve para evitar accesos ilegales, todo lo que este aca esta protegido y solo se puede acceder por servlent
		 */
		System.out.println("Entro al HOME por POST");
		doGet(request, response);
		//request.getRequestDispatcher("WEB-INF/login.html").forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Esta es un PUT");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Esta es un DELETE");
	}

	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Esta es una opcion");
	}
	
	
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Esta es un trace");
	}

}
