package util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;

/**
 * Servlet implementation class Componentes
 */
public class Componentes {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Componentes() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * 
	 */
	public static boolean estaLogeado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("usuario") != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	public static boolean esAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("usuario") != null) {
			Usuario usuario_sesion = (Usuario) request.getSession().getAttribute("usuario");
			
			if(usuario_sesion.getRol().equals("administrador")) {
				return true;
			}
		}
		return false;
	}

}
