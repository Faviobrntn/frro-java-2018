package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMPais;
import controlers.CtrlABMUsuario;
import entity.Pais;
import entity.Usuario;
import util.Emailer;

/**
 * Servlet implementation class Registrarse
 */
@WebServlet({ "/Registrarse", "/registrarse", "/REGISTRARSE" })
public class Registrarse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrarse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getSession().getAttribute("usuario") != null) {
				response.sendRedirect("home/");
			}else {
				//Armo un ArrayList para el select
				CtrlABMPais ctrlPais = new CtrlABMPais();
				ArrayList<Pais> paises;
				paises = ctrlPais.getAll();
				request.setAttribute("paises", paises);
				
				request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", e.getMessage());
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("apellido") == "") { throw new Exception("El apellido no puede estar vacio."); }
			if(request.getParameter("nombre") == "") { throw new Exception("El nombre no puede estar vacio."); }
			if(request.getParameter("email") == "") { throw new Exception("El email no puede estar vacio."); }
			if(request.getParameter("password") == "") { throw new Exception("La contraseña no puede estar vacia."); }
			Usuario user = new Usuario();
			user.setApellido(request.getParameter("apellido"));
			user.setNombre(request.getParameter("nombre"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRol("usuario");
			
			Pais pais = new Pais();
			pais.setId(Integer.parseInt(request.getParameter("id_pais")));
			
			user.setPais(pais);
			
			CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
		
			ctrlUsuario.add(user);
			
			Emailer.getInstance().send(user.getEmail(), "AVerSiAhorra - Su usuario ha sido creado", "Bienvenido "+user.getNombre()+ " a tu billetera virtual! Controla los gastos de manera facil.");
			
			request.getSession().setAttribute("mensaje", "Se ha registrado con éxito!");
			response.sendRedirect("login");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			//request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
			doGet(request, response);
		}
	}

}
