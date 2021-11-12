package dao.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dao.DAO;
import models.order.*;
import models.cloth.*;

public class CartDAO extends DAO{
	public LineItem getLineItem(int clothID, int colorID, int sizeID, int quantity) throws SQLException {
		Cloth cloth = new Cloth();
		Size size = new Size();
		ItemColor itemColor = new ItemColor();
		try {
			cloth = getCloth(clothID);
		} catch (SQLException e) {
			
		}
		
		try {
			size = getSize(sizeID);
		} catch (SQLException e) {
			
		}
		
		try {
			itemColor = getItemColor(clothID, colorID);
		} catch (SQLException e) {
			
		}
		
		LineItem lineItem = new LineItem(cloth, itemColor, size, cloth.getPrice(), quantity);
		return lineItem;
	};
	
	private ItemColor getItemColor(int clothID, int colorID) throws SQLException {
		ItemColor itemColor = new ItemColor();
		Color color = new Color();
		
		String query = "SELECT name, abbreviation, red, green, blue " 
				+ "FROM colors WHERE color_id = " + colorID +" ;";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		if(result.next()) {
			String name = result.getString("name");
			String abbreviation = result.getString("abbreviation");
			int red = result.getInt("red");
			int blue = result.getInt("blue");
			int green = result.getInt("green");
			color = new Color(colorID, name, abbreviation, red, green, blue);
		}
		
		query = "SELECT image_url " 
				+ "FROM itemcolor WHERE cloth_id = " + clothID + " AND color_id = " + colorID +" ;";
		stmt = con.prepareStatement(query);
		result = stmt.executeQuery();
		
		if(result.next()) {
			String url = result.getString("image_url");
			itemColor.setImageUrl(url);
			itemColor.setColor(color);
		} else {
			return null;
		}
		
		return itemColor;
	}

	private static Size getSize(int sizeID) throws SQLException {
		Size size = new Size();
		String query = "SELECT size_name " 
				+ "FROM sizes WHERE size_id = " + sizeID +";";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		if(result.next()) {
			String name = result.getString("size_name");
			
			
			size.setName(name);
			size.setSizeID(sizeID);
		}
		else {
			return null;
		}
		
		return size;
	}

	private static Cloth getCloth(int clothID) throws SQLException {
		Cloth cloth = new Cloth();
		String query = "SELECT name, price, cloth_url " 
				+ "FROM cloths WHERE cloth_id = " + clothID +";";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		if(result.next()) {
			String name = result.getString("name");
			double price = (double) result.getFloat("price");
			String url = result.getString("cloth_url");
			
			cloth.setName(name);
			cloth.setClothId(clothID);
			cloth.setPrice(price);
			cloth.setMainImageUrl(url);
		}
		
		return cloth;
	}
	
	public static void main(String[] args) {
		LineItem line;
		try {
			line = new CartDAO().getLineItem(1, -1, -1, 10);
			try {
				line.getColor().getColor().getColorID();
			} catch (NullPointerException ex) {
				System.out.print("null");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
