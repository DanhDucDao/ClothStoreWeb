package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Category;
import models.ProductType;

public class MenuDAO extends DAO{
	public ArrayList<ProductType> getProductType() throws SQLException {
		ArrayList<ProductType> listProductTypes = new ArrayList<>();
		String SQL = "SELECT * FROM product_types";
		
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet set = stmt.executeQuery();
		while(set.next()) {
			int productTypeId = set.getInt("product_type_id");
			String name = set.getString("name");
			ProductType productType = new ProductType();
			productType.setName(name);
			productType.setProductTypeID(productTypeId);
			
			ArrayList<Category> childCategories = getCategories(productTypeId);
			productType.setListCategories(childCategories);
			listProductTypes.add(productType);
			
		}
		
		return listProductTypes;
	}
	
	private ArrayList<Category> getCategories(int productTypeID) throws SQLException {
		ArrayList<Category> listCategories = new ArrayList<>();
		String SQL = "SELECT * FROM categories WHERE product_type_id = ?";
		
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, productTypeID);
		ResultSet set = stmt.executeQuery();
		while(set.next()) {
			int categoryId = set.getInt("category_id");
			String name = set.getString("name");
			
			Category category = new Category();
			category.setName(name);
			category.setCategoryID(categoryId);
			listCategories.add(category);
		}
		return listCategories;
	}
	
	public Category getCategoryByID(int categoryID) throws SQLException {
		Category category = new Category();
		String SQL = "SELECT name FROM categories WHERE category_id = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, categoryID);
		ResultSet set = stmt.executeQuery();
		if(set.next()) {
			String name = set.getString("name");
					
			category.setName(name);
			category.setCategoryID(categoryID);	
		}
		return category;
	}
	
}
