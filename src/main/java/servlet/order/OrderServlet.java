package servlet.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;
import dao.order.CartDAO;
import models.ProductType;
import models.order.LineItem;
import servlets.CookiesUtil;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			setHeaderPage(request, response);
			ArrayList<LineItem> listLineItem;
			listLineItem = getListLineItem(request, response);
			request.setAttribute("listLineItem", listLineItem);
			getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
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
	
	private static ArrayList<LineItem> getListLineItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Cookie cartCookie = CookiesUtil.getCookies("cart", request, response);
		String cartValue = cartCookie.getValue();
		String[] lineItemStrs = cartValue.split("%");
		
		ArrayList<LineItem> listLineItem = new ArrayList<>();
		CartDAO cartDAO = new CartDAO();
		if(cartValue.trim().equals("")) 
			return listLineItem;
		for(String lineItemStr : lineItemStrs) {
			int clothId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "clothId"));
			int colorId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "colorId"));
			int sizeId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "sizeId"));
			int quantity = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "quantity"));
			LineItem lineItem = cartDAO.getLineItem(clothId, colorId, sizeId, quantity);
			listLineItem.add(lineItem);
		}
		return listLineItem;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
