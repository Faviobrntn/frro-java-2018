package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCuenta;
import controlers.CtrlABMMoneda;
import entity.Cuenta;
import entity.Moneda;
import entity.Usuario;
import util.Componentes;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet({ "/cuentas/*", "/Cuentas/*", "/CUENTAS/*", "/Cuentas" })
public class Cuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cuentas() {
        super();
    }
    
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!Componentes.estaLogeado(request, response)) { throw new Exception("No es un usuario activo. Por favor, vuelva a ingresar."); }
			this.user = (Usuario) request.getSession().getAttribute("usuario");
			
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
			
			//Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			
			CtrlABMCuenta ctrlCuentas = new CtrlABMCuenta();
			ArrayList<Cuenta> cuentas = ctrlCuentas.getAll(this.user);
			request.setAttribute("cuentas", cuentas);

			request.getRequestDispatcher("/accounts/index.jsp").forward(request, response);
			
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
				response.sendRedirect("../cuentas/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
			}
			break;
			
		case "/baja":
			try {
				this.baja(request, response);
				response.sendRedirect("../cuentas/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
			}
			break;
			
		case "/modificacion":
			try {
				this.modificacion(request, response);
				request.getRequestDispatcher("/accounts/modificacion.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
			}
			break;
		
		case "/modificar":
			try {
				this.modificar(request, response);
				response.sendRedirect("../cuentas/");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				request.getSession().setAttribute("mensaje", e.getMessage());
			}
			break;
		}
	}
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cuenta cuenta = new Cuenta();
		cuenta.setUsuario(this.user);
		cuenta.setNombre(request.getParameter("nombre"));
		cuenta.setValorInicial(Float.parseFloat(request.getParameter("valor_inicial")));
		cuenta.setColor(request.getParameter("color"));
		cuenta.setTipo(request.getParameter("tipo"));
		cuenta.setDescripcion(request.getParameter("descripcion"));
		
		Date date = new Date();
		cuenta.setCreado(new Timestamp(date.getTime()));
		
		Moneda moneda = new Moneda();
		moneda.setId(Integer.parseInt(request.getParameter("id_moneda")));
		
		cuenta.setMoneda(moneda);
		
		CtrlABMCuenta ctrlCuenta = new CtrlABMCuenta();
		ctrlCuenta.add(cuenta);	
	}
	
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMCuenta ctrlcuenta = new CtrlABMCuenta();			
		request.setAttribute("usuario", ctrlcuenta.getById(cuenta));
		
		//Armo un Array comun para le select
		CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
		ArrayList<Moneda> monedas = ctrlMoneda.getAll();
		request.setAttribute("monedas", monedas);
	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cuenta cuenta = new Cuenta();
		//cuenta.setUsuario(this.user);
		cuenta.setNombre(request.getParameter("nombre"));
		cuenta.setValorInicial(Float.parseFloat(request.getParameter("valor_inicial")));
		cuenta.setColor(request.getParameter("color"));
		cuenta.setTipo(request.getParameter("tipo"));
		cuenta.setDescripcion(request.getParameter("descripcion"));
		
		Moneda moneda = new Moneda();
		moneda.setId(Integer.parseInt(request.getParameter("id_moneda")));
		
		cuenta.setMoneda(moneda);
		
		CtrlABMCuenta ctrlCuenta = new CtrlABMCuenta();
		ctrlCuenta.update(cuenta);
	}
	
	
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		try {
			Cuenta cuenta = new Cuenta();
			cuenta.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMCuenta ctrlCuenta = new CtrlABMCuenta();
			ctrlCuenta.delete(cuenta);
		} catch (Exception e) {
			throw e;
		}
	}

}
