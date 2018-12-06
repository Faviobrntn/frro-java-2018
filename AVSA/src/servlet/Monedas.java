package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMMoneda;
import entity.Moneda;
import util.Componentes;

/**
 * Servlet implementation class Monedas
 */
@WebServlet({"/Monedas", "/monedas/*", "/Monedas/*", "/MONEDAS/*"})
public class Monedas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monedas() {
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
			CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
			
			ArrayList<Moneda> monedas = ctrlMoneda.getAll();
			
			request.setAttribute("monedas", monedas);
			
			request.getRequestDispatcher("/money/index.jsp").forward(request, response);
			
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
				response.sendRedirect("../monedas/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/money/index.jsp").forward(request, response);
			}
			break;
			
		case "/baja":
			try {
				this.baja(request, response);
				response.sendRedirect("../monedas/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/money/index.jsp").forward(request, response);
			}
			break;
			
		case "/modificar":
			try {
				this.modificar(request, response);
				response.getWriter().println(true);
				//response.sendRedirect("../monedas/");
			} catch (Exception e) {
				//response.getWriter().println(e.toString());
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("/money/index.jsp").forward(request, response);

			}
			break;
		}
	}

	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda pai = new Moneda();
		pai.setNombre(request.getParameter("nombre"));
		
		CtrlABMMoneda ctrlPais = new CtrlABMMoneda();
		ctrlPais.add(pai);
			
	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda coin = new Moneda();
		coin.setId(Integer.parseInt(request.getParameter("id")));
		coin.setNombre(request.getParameter("nombre"));
		
		CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
		ctrlMoneda.update(coin);
	}
	
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda pai = new Moneda();
		pai.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
		ctrlMoneda.delete(pai);
	}

}
