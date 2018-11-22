package servlet;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;

import controlers.CtrlABMUsuario;
import entity.Usuario;

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
		//TODO si esta logeado redirigirlo al dashboard, sino que aparezca la pantalla de login
		if(request.getSession().getAttribute("usuario") != null) {
			//Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			response.sendRedirect("home/");
		}else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * request.getSession()  ---> SESSION: Si no existe la crea
	 * CONDICIONES para guardar un objeto en SESION: 
	 * 	-	tiene que ser un JAVA BINS
	 * 	-	ser serializable (public cass User implements Serializable (){} ) 
	 *  -	tenes un construcctor publico y sin parametros
	 *  -	getter y setter para todos sus atributos
	 * 
	 * request.getSession().setAttribute("usuario", u);  User u = new User(); 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		if(request.getSession().getAttribute("usuario") != null) {
			response.sendRedirect("home/");
		}
		
		if(request.getParameter("email") != "") {
			
			if(request.getParameter("password") != "") {
				try {
					Usuario user = new Usuario();
					user.setEmail(request.getParameter("email"));
					user.setPassword(request.getParameter("password"));
					
					CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
				
					user = ctrlUsuario.login(user);
					
					if(user == null) { throw new Exception("Usuario y/o contraseña incorrectos."); }
					request.getSession();
					request.getSession().setAttribute("usuario", user);
					
					//Si esta logeado lo redirijo a la home
					response.sendRedirect("home/");
					
				} catch (Exception e) {
					request.setAttribute("mensajeFlash", e.getMessage());
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}else {
				System.out.print("Contraseña vacia");
				request.setAttribute("mensajeFlash", "Contraseña vacia");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}else {
			System.out.print("Email vacio");
			request.setAttribute("mensajeFlash", "Email vacio");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		
				
				
		//request.getRequestDispatcher("/home").forward(request, response);
		//request.getRequestDispatcher("/Home.jsp").forward(request, response);
		
		
	}

}
