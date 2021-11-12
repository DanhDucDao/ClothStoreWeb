package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.order.CartDAO;
import models.order.*;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddToCartServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			updateCartCookie(request, response);
	}
	
	
	private static void updateCartCookie(HttpServletRequest request, HttpServletResponse response) {
		String newAddRequest = getAddRequest(request);
		if(newAddRequest.equals("unknown")) 
			return;
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Cookie cartCookie = CookiesUtil.getCookies("cart", request, response);
		String cartValue = cartCookie.getValue();
		String newCartValue = calculateCookieValue(newAddRequest, quantity, cartValue);
		cartCookie.setValue(newCartValue);
		response.addCookie(cartCookie);
		
	}
	
	private static void sendCartResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Cookie cartCookie = CookiesUtil.getCookies("cart", request, response);
		String cartValue = cartCookie.getValue();
		String[] lineItemStrs = cartValue.split("%");
		
		ArrayList<LineItem> listLineItem = new ArrayList<>();
		CartDAO cartDAO = new CartDAO();
		for(String lineItemStr : lineItemStrs) {
			int clothId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "clothId"));
			int colorId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "colorId"));
			int sizeId = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "sizeId"));
			int quantity = Integer.parseInt(CookiesUtil.getPropertieValue(lineItemStr, "quantity"));
			LineItem lineItem = cartDAO.getLineItem(clothId, colorId, sizeId, quantity);
			listLineItem.add(lineItem);
		}
		
		String json = new Gson().toJson(listLineItem);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		System.out.println(json);
		out.print(json);
		out.flush();
		
	}
	
	
	private static String getAddRequest(HttpServletRequest request) {
		String colorIDStr = request.getParameter("color");
		String sizeIDStr = request.getParameter("size");
		String clothIDStr = request.getParameter("clothID");
		
		int clothId = -1;
		int colorId = -1;
		int sizeId = -1;
		
		try {
			clothId = Integer.parseInt(clothIDStr);
		} catch (NumberFormatException ex) {
			return "unknown";
		}
		try {
			sizeId = Integer.parseInt(sizeIDStr);
		} catch (NumberFormatException ex) {
			
		}
		try {
			colorId = Integer.parseInt(colorIDStr);
		} catch (NumberFormatException ex) {
			
		}
		
		String newRequestProduct = "clothId=" + clothId + "/" 
				+ "sizeId=" + sizeId +"/colorId=" + colorId + "/";
		return newRequestProduct;
	}


	private static String calculateCookieValue(String newAddRequest, int quantity, String cartValue) {
		String newCartValue = "";
		String itemStrs[] = cartValue.split("%");
		
		int matchItem = -1;
		for(int i = 0; i<itemStrs.length; i++ ) {
			if(itemStrs[i].indexOf(newAddRequest) >= 0) {
				matchItem = i;
				break;
			}
		}
		
		if(matchItem == -1) {
			newAddRequest += "quantity=" + quantity +"%";
			newCartValue += cartValue + newAddRequest;
		}
		else {
			String productStr = itemStrs[matchItem];
			String properties[] = productStr.split("/");
			String oldQuantityStr = properties[properties.length-1].split("=")[1];
			int oldQuantity = Integer.parseInt(oldQuantityStr);
			int newQuantity = oldQuantity + quantity;
			newAddRequest += "quantity=" + newQuantity; 
			itemStrs[matchItem] = newAddRequest;
			
			newCartValue = "";
			for(String item : itemStrs) {
				newCartValue += item + "%";
			}
		}
		
		return newCartValue;
	}
	

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
