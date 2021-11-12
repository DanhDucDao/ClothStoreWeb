package dao;
import java.util.*;
import models.cloth.*;
import java.sql.*;

public class FilterDAO extends DAO{
	public ArrayList<Color> getAllColors() throws SQLException {
		String sql = "SELECT color_id, abbreviation FROM colors";
		PreparedStatement query = con.prepareStatement(sql);
		ResultSet set = query.executeQuery();
		
		ArrayList<Color> list = new ArrayList<Color>();
		while(set.next()) {
			int colorId = set.getInt("color_id");
			String abbreviation = set.getString("abbreviation");
			
			Color color = new Color();
			color.setColorID(colorId);
			color.setAbbreviation(abbreviation);
			list.add(color);
		}
		
		return list;
	}
	
	public ArrayList<Size> getAllSizes() throws SQLException {
		String sql = "SELECT size_id, size_name FROM sizes";
		PreparedStatement query = con.prepareStatement(sql);
		ResultSet set = query.executeQuery();
		
		ArrayList<Size> list = new ArrayList<Size>();
		while(set.next()) {
			int size_id = set.getInt("size_id");
			String sizename = set.getString("size_name");
			
			Size size = new Size();
			size.setSizeID(size_id);
			size.setName(sizename);
			list.add(size);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		try {
			ArrayList<Color> list = new FilterDAO().getAllColors();
			for(Color c : list) {
				System.out.println(c.getColorID() + c.getAbbreviation());
			}
			ArrayList<Size> list1 = new FilterDAO().getAllSizes();
			for(Size c : list1) {
				System.out.println(c.getSizeID() + c.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
