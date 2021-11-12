package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.cloth.Cloth;
import models.cloth.Color;
import models.cloth.ItemColor;
import models.cloth.Size;
import paging.SearchFilter;

public class ClothDAO extends DAO{
	
	public ArrayList<Cloth> getClothByCategory(int categoryID) throws SQLException {
		ArrayList<Cloth> listCloth = new ArrayList<Cloth>();		
		ResultSet setCloth = getCloth(categoryID);
		packSetToList(setCloth, listCloth);	
		return listCloth;
	}

	public int getClothes(SearchFilter filter, int categoryID, int startIndex, int quantity, ArrayList<Cloth> list) throws SQLException {
		boolean emptyFilter = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.cloth_id, a.name, a.cloth_url, a.price \n");
		
		StringBuilder fromStatement = new StringBuilder("FROM cloths AS a, ");
		StringBuilder whereStatement = new StringBuilder("WHERE ");
		if(filter.getSize() != null) {
			emptyFilter = false;
			fromStatement.append("itemsizes AS b, ");
			whereStatement.append("a.cloth_id = b.cloth_id AND b.size_id = " + filter.getSize().getSizeID() + " AND ");
		}
		if(filter.getColor() != null) {
			emptyFilter = false;
			fromStatement.append("itemcolor AS c, ");
			whereStatement.append("a.cloth_id = c.cloth_id AND c.color_id = " + filter.getColor().getColorID() + " AND ");
		}
		if(filter.getMaxPrice() >= 0) {
			emptyFilter = false;
			whereStatement.append("price <= " + filter.getMaxPrice() + " AND ");
		}
		
		if(filter.getMinPrice() >= 0) {
			emptyFilter = false;
			whereStatement.append("price >= " + filter.getMinPrice() + " AND ");
		}
		
		if(categoryID > 0) {
			emptyFilter = false;
			whereStatement.append("a.category_id = " + categoryID + " AND ");
		}
		
		fromStatement.delete(fromStatement.length() - 2, fromStatement.length() - 1);
		if(emptyFilter == false) {
			whereStatement.delete(whereStatement.length() - 4, whereStatement.length());
		}
		else 
			whereStatement.delete(0, whereStatement.length());
		
		String finalQuery = sql.append(fromStatement.toString()).append("\n").append(whereStatement.toString()).append(";").toString();
		System.out.println(finalQuery);
		PreparedStatement stmt = con.prepareStatement(finalQuery);
		ResultSet set = stmt.executeQuery();
		
		
		int totalQuantity = 0;
		int jumpNumber = startIndex * quantity;
		while(jumpNumber-- != 0) {
			if(set.next())
				totalQuantity++;
			
		}
		System.out.println(totalQuantity);
		int counter = 0;
		while (set.next()) {
			int clothId = set.getInt("cloth_id");
			String name = set.getString("name");
			String url = set.getString("cloth_url");
			double price = (double) set.getFloat("price");
			
			Cloth cloth = new Cloth();
			cloth.setClothId(clothId);
			cloth.setName(name);
			cloth.setMainImageUrl(url);
			cloth.setPrice(price);
			
			list.add(cloth);
			counter++;
			totalQuantity++;
			if(counter >= quantity)
				break;
		}
		System.out.println(totalQuantity);
		while(set.next()) 
			totalQuantity++;
		System.out.println(totalQuantity);
		return totalQuantity;
	}
	
	
	private void packSetToList(ResultSet setCloth, ArrayList<Cloth> listCloth) throws SQLException {
		while(setCloth.next()) {
			int clothID = setCloth.getInt("cloth_id");
			String name = setCloth.getString("name");
			String mainImageUrl = setCloth.getString("cloth_url");
			
			Cloth cloth = new Cloth();
			cloth.setClothId(clothID);
			cloth.setName(name);
			cloth.setMainImageUrl(mainImageUrl);
			
			listCloth.add(cloth);
		}
	}

	private ResultSet getCloth(int categoryID) throws SQLException {
		String SQL = "SELECT cloth_id, name, cloth_url "
				+ "FROM cloths " 
				+ "WHERE category_id = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, categoryID);
		
		ResultSet result = stmt.executeQuery();
		return result;
	}
	
	public Cloth getClothDetail(int clothID) throws SQLException {
		Cloth cloth = new Cloth();
		getClothInfo(clothID, cloth);
		getAvaiableColors(clothID, cloth);
		getAvaiableSizes(clothID, cloth);
		return cloth;
	}
	
	private void getClothInfo(int clothID, Cloth cloth) throws SQLException {
		String SQL = "SELECT * FROM cloths WHERE cloth_id = ?;";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, clothID);
		ResultSet resultSet = stmt.executeQuery();
		
		if(resultSet.next()) {
			
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String mainImageUrl = resultSet.getString("cloth_url");
			
			cloth.setClothId(clothID);
			cloth.setDesciption(description);
			cloth.setMainImageUrl(mainImageUrl);
			cloth.setName(name);
		}
	}
	
	private void getAvaiableColors(int clothID, Cloth cloth) throws SQLException {
		String SQL = "SELECT c.*, b.image_url "
				+ "FROM cloths AS a, itemcolor AS b, colors AS c "
				+ "WHERE a.cloth_id = ? AND b.cloth_id = a.cloth_id AND b.color_id = c.color_id;";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, clothID);
		ResultSet resultSet = stmt.executeQuery();
		
		while(resultSet.next()) {		
			int colorID = resultSet.getInt("color_id");
			String name = resultSet.getString("name");
			String abbreviation = resultSet.getString("abbreviation");
			int red = resultSet.getInt("red");
			int green = resultSet.getInt("green");
			int blue = resultSet.getInt("blue");
			String imageUrl = resultSet.getString("image_url");
			
			Color color = new Color(colorID, name, abbreviation, red, green, blue);
			ItemColor itemColor = new ItemColor(color, imageUrl);
			cloth.addColor(itemColor);
		}
		
	}

	private void getAvaiableSizes(int clothID, Cloth cloth) throws SQLException {
		String SQL = "SELECT c.* "
				+ "FROM cloths AS a, itemsizes AS b, sizes AS c "
				+ "WHERE a.cloth_id = ? AND b.cloth_id = a.cloth_id AND b.size_id = c.size_id;";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, clothID);
		ResultSet resultSet = stmt.executeQuery();
		
		while(resultSet.next()) {		
			int sizeID = resultSet.getInt("size_id");
			String name = resultSet.getString("size_name");
			
			Size size = new Size(sizeID, name);
			cloth.addSize(size);
		}
		
	}
	
	public ArrayList<String> getImageURLs(int clothID) throws SQLException {
		ArrayList<String> listUrls = new ArrayList<String>();
		String SQL = "SELECT * FROM clothimages WHERE cloth_id = ?;";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setInt(1, clothID);
		ResultSet resultSet = stmt.executeQuery();
		
		while(resultSet.next()) {
			String url = resultSet.getString("imageURL");
			listUrls.add(url);
		}
		return listUrls;
	}

	public static void main(String args[]) {
		try {
			SearchFilter filter = new SearchFilter();
			filter.setMaxPrice(900000);
			filter.setMinPrice(300000);
			ArrayList<Cloth> list = new ArrayList<>();
			int totalQuantity = new ClothDAO().getClothes(filter, 1, 0, 5, list);
					
			for(Cloth cloth : list) {
				System.out.println(cloth.getClothId() + " " + cloth.getName() + " " + cloth.getMainImageUrl() + " " + cloth.getPrice());
			}
			System.out.println(totalQuantity);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
