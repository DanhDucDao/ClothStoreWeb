package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClothDAO;
import dao.MenuDAO;
import models.ProductType;
import models.cloth.Cloth;
import models.Category;

/**
 * @Servlet implementation class CategoryViewerServlet
 */
@WebServlet("/category")
public class CategoryViewerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryViewerServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		setHeaderPage(request, response);
		setProductPageHeader(request, response);
		setProductPageContent(request, response);
		dispatch(request, response);
	}


	private void setProductPageHeader(HttpServletRequest request, HttpServletResponse response) {
		try {
			String categoryIDStr = request.getParameter("id");
			int categoryID = Integer.parseInt(categoryIDStr);
			Category category = new MenuDAO().getCategoryByID(categoryID);
			request.setAttribute("category", category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setHeaderPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			ArrayList<ProductType> listProductType = new MenuDAO().getProductType();
			request.setAttribute("listMenu", listProductType);
		}
		catch (Exception ex) {
			
		}
	}
	
	private void setProductPageContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			String categoryIDStr = request.getParameter("id");
			int categoryID = Integer.parseInt(categoryIDStr);
			ArrayList<Cloth> listClothes = new ClothDAO().getClothByCategory(categoryID);
			request.setAttribute("listClothes", listClothes);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			getServletContext().getRequestDispatcher("/product.jsp")
												.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
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
