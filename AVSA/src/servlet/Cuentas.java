package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCuentas;
import controlers.CtrlABMMoneda;
import entity.Cuenta;
import entity.Moneda;
import entity.Usuario;
import util.Componentes;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet({ "/Cuentas/*", "/Cuentas/*", "/CUENTAS/*", "/Cuentas" })
public class Cuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cuentas() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!Componentes.estaLogeado(request, response)) { throw new Exception("No es un usuario activo. Por favor, vuelva a ingresar."); }
			
			String method = request.getMethod();
			
		    if (method.equals("GET")) {
		    	doGet(request, response);
		    } else if (method.equals("POST")) {
		        doPost(request, response);
		    }

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("mensaje", e.getMessage());
			response.sendRedirect("../login");
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
						CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
						ArrayList<Moneda> monedas = ctrlMoneda.getAll();
						request.setAttribute("monedas", monedas);
						request.getRequestDispatcher("/accounts/alta.jsp").forward(request, response);
					} catch (Exception e) {
						throw e;
					}
					break;
			}
			
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			
			CtrlABMCuentas ctrlCuentas = new CtrlABMCuentas();
			ArrayList<Cuenta> cuentas = ctrlCuentas.getAll(user);
			request.setAttribute("cuentas", cuentas);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
