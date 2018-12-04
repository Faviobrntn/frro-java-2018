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

import controlers.CtrlABMCategoria;
import controlers.CtrlABMCuenta;
import controlers.CtrlABMRegistro;
import controlers.CtrlABMRegistro;
import entity.Categoria;
import entity.Cuenta;
import entity.Registro;
import entity.Moneda;
import entity.Registro;
import entity.Usuario;
import util.Componentes;

/**
 * Servlet implementation class Registros
 */
@WebServlet({ "/registros", "/Registros", "/REGISTROS", "/registros/*" })
public class Registros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registros() {
        super();
        // TODO Auto-generated constructor stub
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
						CtrlABMCuenta ctrlCuentas = new CtrlABMCuenta();
						ArrayList<Cuenta> cuentas = ctrlCuentas.getAll(this.user);
						request.setAttribute("cuentas", cuentas);
						
						//Armo un ArrayList para el select
						CtrlABMCategoria ctrlCategorias = new CtrlABMCategoria();
						ArrayList<Categoria> categorias = ctrlCategorias.getAll();
						request.setAttribute("categorias", categorias);
						
						request.getRequestDispatcher("/registers/alta.jsp").forward(request, response);
					} catch (Exception e) {
						throw e;
					}
					break;
			}
			
			//Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			
			CtrlABMRegistro ctrlRegistros = new CtrlABMRegistro();
			ArrayList<Registro> registro = ctrlRegistros.getAll(this.user);
			request.setAttribute("registro", registro);

			request.getRequestDispatcher("/registers/index.jsp").forward(request, response);
			
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
					response.sendRedirect("../registros/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/registers/index.jsp").forward(request, response);
				}
				break;
				
			case "/baja":
				try {
					this.baja(request, response);
					response.sendRedirect("../registros/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/registers/index.jsp").forward(request, response);
				}
				break;
				
			case "/modificacion":
				try {
					this.modificacion(request, response);
					request.getRequestDispatcher("/registers/modificacion.jsp").forward(request, response);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/registers/index.jsp").forward(request, response);
				}
				break;
			
			case "/modificar":
				try {
					this.modificar(request, response);
					response.sendRedirect("../registros/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/registers/index.jsp").forward(request, response);
				}
				break;
		}
	}
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Registro registro = new Registro();
		
		registro.setTipo(request.getParameter("tipo"));
		registro.setFechaHora(request.getParameter("fecha_hora"));
		registro.setImporte(request.getParameter("importe"));
		registro.setEstado(request.getParameter("estado"));
		registro.setLugar(request.getParameter("lugar"));
		registro.setNotas(request.getParameter("notas"));

		Date date = new Date();
		registro.setCreado(new Timestamp(date.getTime()));
		registro.setModificado(new Timestamp(date.getTime()));

		Cuenta cuenta = new Cuenta();
		Categoria categoria = new Categoria();
		cuenta.setId(Integer.parseInt(request.getParameter("cuenta_id")));
		categoria.setId(Integer.parseInt(request.getParameter("categoria_id")));
		
		registro.setUsuario(this.user);
		registro.setCuenta(cuenta);
		registro.setCategoria(categoria);
		
		CtrlABMRegistro ctrlRegistro = new CtrlABMRegistro();
		ctrlRegistro.add(registro);
	}

}
