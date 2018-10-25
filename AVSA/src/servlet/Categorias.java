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
				System.out.println("metodo agregar");
				try {
					this.alta(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().append("Excepcion: ");
				}
				break;
				
			case "/baja":
				//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through get");
				this.baja(request, response);
				break;
				
			case "/modificacion":
				//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through get");
				this.modificacion(request, response);
				break;
		}
	
		response.sendRedirect("../categorias/");
	}
	
	
	
	private void alta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Es un ALTA");
		Categoria categ = new Categoria();
		categ.setNombre(request.getParameter("nombre"));
		categ.setDescripcion(request.getParameter("descripcion"));
		
		CtrlABMCategoria ctrlCategoria = new CtrlABMCategoria();
		ctrlCategoria.add(categ);
			
	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MODIFICACIOOOOON");
	}
	
	private void baja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DELETEEEEEE");
	}

}
