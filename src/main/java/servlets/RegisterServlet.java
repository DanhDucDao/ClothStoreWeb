package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfileDAO;
import models.order.Account;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String phoneNumber = request.getParameter("phonenumber").trim();
		String email = request.getParameter("email").trim();
		
		String usernameHint = "";
		String passwordHint = "";
		String phoneNumberHint = "";
		String emailHint = "";
		boolean isSuccess = true;
		if(username.equals("")) {
			isSuccess = false;
			usernameHint = "Ten khong duoc bo trong";
		}
		if(password.equals("")) {
			isSuccess = false;
			passwordHint = "Mat khau khong duoc bo trong";
		}
		if(phoneNumber.equals("")) {
			isSuccess = false;
			phoneNumberHint = "So dien thoai khong duoc bo trong";
		}
		if(email.equals("")) {
			isSuccess = false;
			emailHint = "email khong duoc bo trong";
		}
		
		String result = "";
		if(isSuccess == true) {
			Account acount = new Account();
			acount.setUsername(username);
			acount.setPhoneNumber(phoneNumber);
			acount.setEmail(email);
			acount.setPassword(password);
			
			try {
				result = new ProfileDAO().addProfile(acount);
			} catch (SQLException e) {
				isSuccess = false;
				e.printStackTrace();
			}
		}
		
		if(isSuccess == true) {
			if(result.equalsIgnoreCase("usernameReplicate")) {
				isSuccess = false;
				usernameHint = "Ten bi trung";
			}
		}
		
		if(isSuccess == false) {
			request.setAttribute("password", password);
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("usernameHint", usernameHint);
			request.setAttribute("passwordHint", passwordHint);
			request.setAttribute("emailHint", emailHint);
			request.setAttribute("phoneNumberHint", phoneNumberHint);
			doGet(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}

}
