package servlet.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ClientInfoStatus;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.order.CartDAO;
import dao.order.OrderDAO;
import models.order.Account;
import models.order.Address;
import models.order.LineItem;
import models.order.Order;
import models.order.OrderInfo;
import servlets.CookiesUtil;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckoutServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				OrderInfo info = bindRequest(request, response);
				Order order;
				order = makeOrder(info, request, response);
				int orderId = new OrderDAO().addOrder(order);
				if(orderId != -1) {
					resetCartCookie(request, response);
				}
				
				String json = new Gson().toJson(orderId);
				System.out.println(json);
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				writer.write(json);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}


	private void resetCartCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cartCook = CookiesUtil.getCookies("cart", request, response);
		cartCook.setValue("");
		response.addCookie(cartCook);
		return;
	}


	private Order makeOrder(OrderInfo info, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		ArrayList<LineItem> listItems = getListLineItem(request, response);
		Order order = new Order();
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if(account != null) {
			order.setAccount(account);
		}
		order.setInfo(info);
		order.setListItems(listItems);
		return order;
	}
	

	private OrderInfo bindRequest(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		String street = request.getParameter("street");
		String district = request.getParameter("district");
		String subDistrict = request.getParameter("subDistrict");
		String city = request.getParameter("city");
		//String payMethod = request.getParameter("payMethod");
		
		Address address = new Address(street, subDistrict, district, city);
		OrderInfo info = new OrderInfo();
		info.setAddress(address);
		info.setName(name);
		info.setPhoneNumber(phoneNumber);
		info.setEmail(email);
		
		return info;
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

}
