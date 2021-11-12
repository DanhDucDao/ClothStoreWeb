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

/**
 * @Servlet implementation class ProductDetailsServlet
 * @author LAPTOP
 */
@WebServlet("/viewProductDetail")
public class ViewProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public  ViewProductDetail() {
        super();

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			setHeaderPage(request, response);
			setProductContent(request, response);
			dispatch(request, response, "/product-detail.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String dest) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(dest).forward(request, response);	
	}

	private void setProductContent(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String idStr = request.getParameter("id");
		int clothID = Integer.parseInt(idStr);
		ClothDAO dao = new ClothDAO();
		Cloth cloth = dao.getClothDetail(clothID);
		ArrayList<String> listImageUrls = dao.getImageURLs(clothID);
		
		request.setAttribute("cloth", cloth);
		request.setAttribute("listImages", listImageUrls);
	}

	private void setHeaderPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			ArrayList<ProductType> listProductType = new MenuDAO().getProductType();
			request.setAttribute("listMenu", listProductType);
		}
		catch (Exception ex) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
