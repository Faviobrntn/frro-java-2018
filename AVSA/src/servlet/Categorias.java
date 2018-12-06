package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCategoria;
import entity.Categoria;
import util.Componentes;

/**
 * Servlet implementation class Categorias
 */
@WebServlet({ "/Categorias", "/categorias/*", "/Categorias/*", "/CATEGORIAS/*" })
public class Categorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorias() {
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
			CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
			
			ArrayList<Categoria> categorias = ctrlCategoria.getAll();
			
			//request.getSession().setAttribute("orderToShow", order);
			request.setAttribute("categorias", categorias);
			
			request.getRequestDispatcher("/categories/index.jsp").forward(request, response);
			
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
					response.sendRedirect("../categorias/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/categories/index.jsp").forward(request, response);
				}
				break;
				
			case "/baja":
				//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through get");
				try {
					this.baja(request, response);
					response.sendRedirect("../categorias/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/categories/index.jsp").forward(request, response);
				}
				break;
				
			case "/modificacion":
				//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
				try {
					this.modificacion(request, response);
					request.getRequestDispatcher("/categories/modificacion.jsp").forward(request, response);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/categories/index.jsp").forward(request, response);
				}
				break;
			
			case "/modificar":
				//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
				try {
					this.modificar(request, response);
					response.sendRedirect("../categorias/");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					request.getSession().setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/categories/index.jsp").forward(request, response);
				}
				break;
		}
	}
	
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Es un ALTA");
		Categoria categ = new Categoria();
		categ.setNombre(request.getParameter("nombre"));
		categ.setDescripcion(request.getParameter("descripcion"));
		CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
		ctrlCategoria.add(categ);
			
	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MODIFICACIOOOOON");
		
		try {
			Categoria categ = new Categoria();
			categ.setId(Integer.parseInt(request.getParameter("id")));
			
			CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
			request.setAttribute("categoria", ctrlCategoria.getById(categ));
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MODIFICAR");
		
		Categoria categ = new Categoria();
		categ.setId(Integer.parseInt(request.getParameter("id")));
		categ.setNombre(request.getParameter("nombre"));
		categ.setDescripcion(request.getParameter("descripcion"));
		
		CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
		ctrlCategoria.update(categ);
	}
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ELIMINAR");
		
		Categoria categ = new Categoria();
		categ.setId(Integer.parseInt(request.getParameter("id")));
		
		CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
		ctrlCategoria.delete(categ);
	}

}
