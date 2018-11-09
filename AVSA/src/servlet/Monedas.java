package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMMoneda;
import entity.Moneda;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
			
			ArrayList<Moneda> monedas = ctrlMoneda.getAll();
			
			request.setAttribute("monedas", monedas);
			
			request.getRequestDispatcher("/money/index.jsp").forward(request, response);
			
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
				response.sendRedirect("../monedas/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "/baja":
			try {
				this.baja(request, response);
				response.sendRedirect("../monedas/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/modificacion":
			try {
				this.modificacion(request, response);
				request.getRequestDispatcher("/money/modificacion.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		case "/modificar":
			try {
				this.modificar(request, response);
				response.sendRedirect("../monedas/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
		//JSONArray arrayObj=new JSONArray();

		// Obtengo los datos de la peticion
		String nombre = request.getParameter("nombre");

		// Compruebo que los campos del formulario tienen datos para añadir a la tabla
		if (!nombre.equals("")) {
			Moneda pai = new Moneda();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			pai.setNombre(request.getParameter("nombre"));
			
			CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
			try {
				ctrlMoneda.update(pai);
				//arrayObj.put(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//out.println("</table>");
		out.println(true);
		out.close();

	}
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda pai = new Moneda();
		pai.setNombre(request.getParameter("nombre"));
		
		CtrlABMMoneda ctrlPais = new CtrlABMMoneda();
		ctrlPais.add(pai);
			
	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			Moneda pai = new Moneda();
			pai.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMMoneda ctrlPais = new CtrlABMMoneda();
			request.setAttribute("pais", ctrlPais.getById(pai));
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda pai = new Moneda();
		pai.setId(Integer.parseInt(request.getParameter("id")));
		pai.setNombre(request.getParameter("nombre"));
		
		CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
		ctrlMoneda.update(pai);
	}
	
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Moneda pai = new Moneda();
		pai.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMMoneda ctrlMoneda = new CtrlABMMoneda();
		ctrlMoneda.delete(pai);
	}

}
