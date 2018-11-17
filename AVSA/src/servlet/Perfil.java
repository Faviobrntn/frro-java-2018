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

/**
 * Servlet implementation class Perfil
 */
@WebServlet({ "/Perfil", "/perfil", "/perfil/*" })
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Perfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get - Perfil");
		if(request.getSession().getAttribute("usuario") != null) {
			System.out.println(request.getPathInfo());
			
			switch (request.getPathInfo()) {
			case "/cerrar-sesion":
				try {
					System.out.println("CERRAR SESION");
					request.getSession().invalidate();
					response.sendRedirect("../login");
				    return; // <--- Here.
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			
			try {
				//Armo un Array comun para le select
				CtrlABMPais ctrlPais = new CtrlABMPais();
				ArrayList<Pais> paises;
				paises = ctrlPais.getAll();
				request.setAttribute("paises", paises);
				//request.getRequestDispatcher("/index.html").forward(request, response);
				
				request.getRequestDispatcher("/profile/index.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("../login");
			}
		}else {
			//request.getRequestDispatcher("/index.html").forward(request, response);
			response.sendRedirect("../login");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("usuario") != null) {
						
			try {
				Usuario usuario_sesion = (Usuario) request.getSession().getAttribute("usuario");
				
				Usuario user = new Usuario();
				user.setId(Integer.parseInt(request.getParameter("id")));
				user.setApellido(request.getParameter("apellido"));
				user.setNombre(request.getParameter("nombre"));
				
				//user.setId(Integer.parseInt(usuario_sesion.getId()));
				user.setEmail(usuario_sesion.getEmail());
				user.setRol(usuario_sesion.getRol());
				
				Pais pais = new Pais();
				pais.setId(Integer.parseInt(request.getParameter("id_pais")));
				
				user.setPais(pais);
				
				CtrlABMUsuario ctrlUsuario = new CtrlABMUsuario();
				ctrlUsuario.update(user);
				
				//request.getRequestDispatcher("/profile/index.jsp").forward(request, response);
				doGet(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//request.getRequestDispatcher("/profile/index.jsp").forward(request, response);
			}
		
		}else{
			response.sendRedirect("../login");
		}
		
	}

}
