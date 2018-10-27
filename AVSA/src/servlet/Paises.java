package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMPais;
import entity.Pais;

/**
 * Servlet implementation class Paises
 */
@WebServlet({"/Paises", "/paises/*", "/Paises/*", "/PAISES/*"})
public class Paises extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paises() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			CtrlABMPais ctrlPais = new CtrlABMPais();
			
			ArrayList<Pais> paises = ctrlPais.getAll();
			
			//request.getSession().setAttribute("orderToShow", order);
			request.setAttribute("paises", paises);
			
			request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "/baja":
			//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through get");
			try {
				this.baja(request, response);
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/modificacion":
			//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
			try {
				this.modificacion(request, response);
				request.getRequestDispatcher("/countries/modificacion.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		case "/modificar":
			//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
			try {
				this.modificar(request, response);
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
	}
}
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Es un ALTA");
		Pais pai = new Pais();
		pai.setNombre(request.getParameter("nombre"));

		
		CtrlABMPais ctrlPais = new CtrlABMPais();
		ctrlPais.add(pai);
			
	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MODIFICACION");
		
		try {
			Pais pai = new Pais();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMPais ctrlPais = new CtrlABMPais();
			request.setAttribute("pais", ctrlPais.getById(pai));
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MODIFICAR");
		
		Pais pai = new Pais();
		pai.setId(Integer.parseInt(request.getParameter("id")));
		pai.setNombre(request.getParameter("nombre"));
		
		CtrlABMPais ctrlPais = new CtrlABMPais();
		ctrlPais.update(pai);
	}
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ELIMINAR");
		
		Pais pai = new Pais();
		pai.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMPais ctrlPais = new CtrlABMPais();
		ctrlPais.delete(pai);
	}

}
