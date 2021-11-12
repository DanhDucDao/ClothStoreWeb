package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClothDAO;
import dao.FilterDAO;
import dao.MenuDAO;
import models.ProductType;
import models.cloth.Cloth;
import models.cloth.Color;
import models.cloth.Size;
import paging.SearchFilter;

@WebServlet("/product")
public class ProductViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProductViewer() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			setHeaderPage(request, response);
			loadPage(request, response);
			setFilter(request, response);
			getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private void setFilter(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		FilterDAO accessPoint = new FilterDAO();
		ArrayList<Color> listColor = accessPoint.getAllColors();
		ArrayList<Size> listSize = accessPoint.getAllSizes();
		
		request.setAttribute("listColor", listColor);
		request.setAttribute("listSize", listSize);	
	}


	private void setHeaderPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			ArrayList<ProductType> listProductType = new MenuDAO().getProductType();
			request.setAttribute("listMenu", listProductType);
		}
		catch (Exception ex) {
			
		}
	}

	private void loadPage(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SearchFilter filter = getFilter(request);
		int categoryId = getCategoryId(request);
		int startIndex = getPageIndex(request);
		int quantity = 5;
		
		ArrayList<Cloth> list = new ArrayList<>();
		int totalQuantity =	new ClothDAO().getClothes(filter, categoryId, startIndex, quantity, list);
		
		int startDisplay = startIndex * quantity + 1;
		if(startDisplay >= totalQuantity)
			startDisplay = totalQuantity;
		int endDisplay = startDisplay - 1 + quantity;
		if(endDisplay > totalQuantity)
			endDisplay = totalQuantity;
		
		request.setAttribute("listClothes", list);
		request.setAttribute("startDisplay", startDisplay);
		request.setAttribute("endDisplay", endDisplay);
		request.setAttribute("quantityDisplay", quantity);
		request.setAttribute("totalQuantity", totalQuantity);
		for(Cloth cloth : list) {
			System.out.println(cloth.getClothId() + " " + cloth.getName() + " " + cloth.getPrice());
		}
		
		
		
	}

	private int getPageIndex(HttpServletRequest request) {
		String indexStr = request.getParameter("page");
		if(indexStr != null) {
			return Integer.parseInt(indexStr) - 1;
		}
		return 0;
	}


	private SearchFilter getFilter(HttpServletRequest request) {
		String colorIdStr = request.getParameter("colorId");
		String sizeIdStr = request.getParameter("sizeId");
		String priceRangeStr = request.getParameter("price");
		
		SearchFilter filter = new SearchFilter();
		
		int colorId = -1;
		int sizeId = -1;
		
		
		if(colorIdStr != null) {
			colorId = Integer.parseInt(colorIdStr);
			Color color = new Color();
			color.setColorID(colorId);
			filter.setColor(color);
		}
		
		if(sizeIdStr != null) {
			sizeId = Integer.parseInt(sizeIdStr);
			Size size = new Size();
			size.setSizeID(sizeId);
			filter.setSize(size);
		}
		
		if(priceRangeStr != null) {
			StringTokenizer tokens = new StringTokenizer(priceRangeStr, ":");
			String minPriceStr = tokens.nextToken();
			String maxPriceStr = tokens.nextToken();
			
			double minPrice = Double.parseDouble(minPriceStr);
			double maxPrice = Double.parseDouble(maxPriceStr);
			
			filter.setMinPrice(minPrice);
			filter.setMaxPrice(maxPrice);
		}
		return filter;
	}
	
	private int getCategoryId(HttpServletRequest request) {
		String categoryIdStr = request.getParameter("categoryId");
		if(categoryIdStr != null) {
			return Integer.parseInt(categoryIdStr);
		}
		
		return 0;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
