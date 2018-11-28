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
import util.Componentes;

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
    }
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!Componentes.esAdmin(request, response)) { throw new Exception("No tiene permiso para realizar esta acción."); }
			
			String method = request.getMethod();
			
		    if (method.equals("GET")) {
		    	doGet(request, response);
		    } else if (method.equals("POST")) {
		        doPost(request, response);
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			response.sendRedirect("../home/");
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			switch (request.getPathInfo()) {
				case "/alta":
					try {
						//Armo un ArrayList para el select
						CtrlABMPais ctrlPais = new CtrlABMPais();
						ArrayList<Pais> paises = ctrlPais.getAll();
						request.setAttribute("paises", paises);
						request.getRequestDispatcher("/users/alta.jsp").forward(request, response);
					} catch (Exception e) {
						throw e;
					}
					break;
			}
			
			CtrlABMUsuario ctrlUser = new CtrlABMUsuario();			
			ArrayList<Usuario> usuarios = ctrlUser.getAll();
			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher("/users/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getPathInfo()) {
		case "/alta":
			try {
				this.alta(request, response);
				response.sendRedirect("../usuarios/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/users/index.jsp").forward(request, response);
			}
			break;
			
		case "/baja":
			try {
				this.baja(request, response);
				response.sendRedirect("../usuarios/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/users/index.jsp").forward(request, response);
			}
			break;
			
		case "/modificacion":
			try {
				this.modificacion(request, response);
				request.getRequestDispatcher("/users/modificacion.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/users/index.jsp").forward(request, response);
			}
			break;
		
		case "/modificar":
			try {
				this.modificar(request, response);
				response.sendRedirect("../usuarios/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/users/index.jsp").forward(request, response);
			}
			break;
		}
	}
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario user = new Usuario();
		user.setApellido(request.getParameter("apellido"));
		user.setNombre(request.getParameter("nombre"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRol(request.getParameter("rol"));
		
		Pais pais = new Pais();
		pais.setId(Integer.parseInt(request.getParameter("id_pais")));
		
		user.setPais(pais);
		
		CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
		ctrlUsuario.add(user);
		
	}
	
	
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario user = new Usuario();
		user.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMUsuario ctrlUser = new CtrlABMUsuario();			
		request.setAttribute("usuario", ctrlUser.getById(user));
		
		//Armo un Array comun para le select
		CtrlABMPais ctrlPais = new CtrlABMPais();
		ArrayList<Pais> paises = ctrlPais.getAll();
		request.setAttribute("paises", paises);
		
	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario user = new Usuario();
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setApellido(request.getParameter("apellido"));
		user.setNombre(request.getParameter("nombre"));
		user.setEmail(request.getParameter("email"));
		user.setRol(request.getParameter("rol"));
		
		Pais pais = new Pais();
		pais.setId(Integer.parseInt(request.getParameter("id_pais")));
		
		user.setPais(pais);
		
		CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
		ctrlUsuario.update(user);
		
	}
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		try {
			Usuario user = new Usuario();
			user.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
			ctrlUsuario.delete(user);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
