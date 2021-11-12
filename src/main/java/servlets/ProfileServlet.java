package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MenuDAO;
import dao.ProfileDAO;
import models.ProductType;
import models.order.Account;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ProfileServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null) {
			response.sendRedirect("/ClothStoreWebDevelopement/login.jsp");
		}
		else {
			setHeaderPage(request, response);
			getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("user");
		account.setEmail(email);
		account.setPassword(password);
		account.setPhoneNumber(phoneNumber);
		
		try {
			new ProfileDAO().updateProfile(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("user", account);
		doGet(request, response);
	}

}
