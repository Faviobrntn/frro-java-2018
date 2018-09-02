package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testLogin
 */
@WebServlet({ "/home", "/Home", "/HOME"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		System.out.println(request.getParameter("email"));
		//doGet(request, response);
		//request.getRequestDispatcher("WEB-INF/login.html").forward(request, response);
	}

}
