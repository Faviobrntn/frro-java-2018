package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMMoneda;
import controlers.CtrlABMPais;
import entity.Moneda;
import entity.Pais;
import util.Componentes;

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
			CtrlABMPais ctrlPais = new CtrlABMPais();
			
			ArrayList<Pais> paises = ctrlPais.getAll();

			//request.getSession().setAttribute("orderToShow", order);
			request.setAttribute("paises", paises);
			
			//Armo un ArrayList para el select
			CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
			ArrayList<Moneda> monedas = ctrlMoneda.getAll();
			request.setAttribute("monedas", monedas);
			
			request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			
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
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			}
			break;
			
		case "/baja":
			try {
				this.baja(request, response);
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			}
			break;
			
		case "/modificacion":
			try {
				this.modificacion(request, response);
				request.getRequestDispatcher("/countries/modificacion.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			}
			break;
		
		case "/modificar":
			try {
				this.modificar(request, response);
				response.sendRedirect("../paises/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/countries/index.jsp").forward(request, response);
			}
			break;
	}
}
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Pais pai = new Pais();
			pai.setNombre(request.getParameter("nombre"));
			
			Moneda money = new Moneda();
			money.setId(Integer.parseInt(request.getParameter("id_moneda")));
			
			pai.setMoneda(money);
			
			CtrlABMPais ctrlPais = new CtrlABMPais();
			ctrlPais.add(pai);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			Pais pai = new Pais();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMPais ctrlPais = new CtrlABMPais();
			request.setAttribute("pais", ctrlPais.getById(pai));
			
			//Armo un Array comun para le select
			CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
			ArrayList<Moneda> monedas = ctrlMoneda.getAll();
			request.setAttribute("monedas", monedas);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			Pais pai = new Pais();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			pai.setNombre(request.getParameter("nombre"));
			
			Moneda money = new Moneda();
			money.setId(Integer.parseInt(request.getParameter("id_moneda")));
			
			pai.setMoneda(money);
			
			CtrlABMPais ctrlPais = new CtrlABMPais();
			ctrlPais.update(pai);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Pais pai = new Pais();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMPais ctrlPais = new CtrlABMPais();
			ctrlPais.delete(pai);
		} catch (Exception e) {
			throw e;
		}
	}

}
