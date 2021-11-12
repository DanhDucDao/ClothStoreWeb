package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilterDAO;
import models.cloth.*;

@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FilterServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FilterDAO accessPoint = new FilterDAO();
			ArrayList<Color> listColor = accessPoint.getAllColors();
			ArrayList<Size> listSize = accessPoint.getAllSizes();
			
			request.setAttribute("listColor", listColor);
			request.setAttribute("listSize", listSize);
			getServletContext().getRequestDispatcher("/filter.jsp").forward(request, response);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
