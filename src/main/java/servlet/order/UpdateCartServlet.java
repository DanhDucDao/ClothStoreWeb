package servlet.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import servlets.CookiesUtil;

@WebServlet("/cartUpdate")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCartServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("op");
		switch (operation) {
			case "update" :
				updateCart(request, response);
				break;
			case "delete" :
				deleteProduct(request, response);
				break;
		}
		
		String responseData = (String) request.getAttribute("response");
		String json = new Gson().toJson(responseData);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	

	private void updateCart(HttpServletRequest request, HttpServletResponse response) {
		String responseMessage = "200";
		
		String clothId = request.getParameter("clothId");
		String colorId = request.getParameter("colorId");
		String sizeId = request.getParameter("sizeId");
		String quantity = request.getParameter("quantity");
		
		String requestCookieStr = "clothId=" + clothId +"/" 
				+ "sizeId=" + sizeId + "/"
				+ "colorId=" + colorId + "/" ;
		
		Cookie cartCookie = CookiesUtil.getCookies("cart", request, response);
		String matchStr = findMatch(requestCookieStr, cartCookie);
		if(matchStr.equals("unknown"))
			responseMessage = "400";
		requestCookieStr = requestCookieStr + "quantity=" + quantity;
		String cartValue = cartCookie.getValue().replaceAll(matchStr, requestCookieStr);
		
		cartCookie.setValue(cartValue);
		response.addCookie(cartCookie);
		request.setAttribute("response", responseMessage);
		
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String responseMessage = "200";
		String clothId = request.getParameter("clothId");
		String colorId = request.getParameter("colorId");
		String sizeId = request.getParameter("sizeId");
		String quantity = request.getParameter("quantity");
		
		String requestCookieStr = "clothId=" + clothId +"/" 
				+ "sizeId=" + sizeId + "/"
				+ "colorId=" + colorId + "/" ;
		Cookie cartCookie = CookiesUtil.getCookies("cart", request, response);
		String matchStr = findMatch(requestCookieStr, cartCookie);
		if(matchStr.equals("unknown"))
			
			responseMessage = "400";
		System.out.println(matchStr);
		requestCookieStr = matchStr + "%";
		String cartValue = cartCookie.getValue().replace(requestCookieStr, "");
		
		cartCookie.setValue(cartValue);
		response.addCookie(cartCookie);
		request.setAttribute("response", responseMessage);
		
	}
	
	private String findMatch(String testStr, Cookie cookie) {
		String cookieStr = cookie.getValue();
		String sourceStr[] = cookieStr.split("%");
		for(String str : sourceStr) {
			if(str.indexOf(testStr) >= 0)
				return str;
		}
		return "unknown";
	}

}
