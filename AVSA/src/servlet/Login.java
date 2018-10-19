package servlet;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;

/**
 * Servlet implementation class login
 */
@SuppressWarnings("unused")
@WebServlet({ "/login", "/Login", "/LOGIN" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()){
			System.out.println(entry.getKey()+" - "+entry.getValue()[0]);
		}
		//System.out.println(request.getParameter(""));
		if(request.getParameter("alta") != null) {
			System.out.print("Esta es un Alta");
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
		System.out.println("POST");
		
		//Si esta logeado lo redirijo a la home
		response.sendRedirect("/AVerSiAhorra/home");
				
				
		//request.getRequestDispatcher("/home").forward(request, response);
		//request.getRequestDispatcher("/Home.jsp").forward(request, response);
		
		
	}

}
