package dao.order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import dao.DAO;
import models.order.*;
public class OrderDAO extends DAO{
	
	public int addOrder(Order order) {
		try {
			con.setAutoCommit(false);
			int newOrderId = initiateOrder(order);
			addOrderInfor(order.getInfo(), newOrderId);
			addLineItems(order.getListItems(), newOrderId);
			con.setAutoCommit(true);
			return newOrderId;
		} catch (SQLException ex) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				return -1;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
	
	private void addLineItems(ArrayList<LineItem> listItems, int newOrderId) throws SQLException {
		for(LineItem item : listItems) {	
			
			boolean colorNull = false;
			try {
				int colorId = 0;
				colorId = item.getColor().getColor().getColorID();
			} catch (NullPointerException ex) {
				colorNull = true;
			}
			
			
			boolean sizeNull = false;
			try {
				int sizeId = 0;
				sizeId = item.getSize().getSizeID();
			} catch (NullPointerException ex) {
				sizeNull = true;
			}
			
			if(colorNull && sizeNull) {
				addOnly(item, newOrderId);
			} else if(!colorNull && sizeNull) {
				addWithColor(item, newOrderId);
			} else if(colorNull && !sizeNull) {
				addWithSize(item, newOrderId);
			} else {
				addWithBoth(item, newOrderId);
			}
		}
	}
	
	private void addOnly(LineItem item, int newOrderId) throws SQLException {
		String sqlInsert = "INSERT INTO lineitems(cloth_id, price, quantity, order_id) "
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sqlInsert);
		stmt.setInt(1, item.getCloth().getClothId());
		stmt.setFloat(2, (float) item.getPrice());
		stmt.setInt(3, item.getQuantity());
		stmt.setInt(4, newOrderId);
		
		stmt.execute();
		
	}
	
	private void addWithColor(LineItem item, int newOrderId) throws SQLException {
		String sqlInsert = "INSERT INTO lineitems(cloth_id, price, quantity, order_id, color_id) "
				+ "VALUES(?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sqlInsert);
		stmt.setInt(1, item.getCloth().getClothId());
		stmt.setFloat(2, (float) item.getPrice());
		stmt.setInt(3, item.getQuantity());
		stmt.setInt(4, newOrderId);
		stmt.setInt(5, item.getColor().getColor().getColorID());
		
		stmt.execute();
	}
	
	private void addWithSize(LineItem item, int newOrderId) throws SQLException {
		String sqlInsert = "INSERT INTO lineitems(cloth_id, price, quantity, order_id, size_id) "
				+ "VALUES(?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sqlInsert);
		stmt.setInt(1, item.getCloth().getClothId());
		stmt.setFloat(2, (float) item.getPrice());
		stmt.setInt(3, item.getQuantity());
		stmt.setInt(4, newOrderId);
		stmt.setInt(5, item.getSize().getSizeID());
		
		stmt.execute();
	}
	
	private void addWithBoth(LineItem item, int newOrderId) throws SQLException {
		String sqlInsert = "INSERT INTO lineitems(cloth_id, price, quantity, order_id, size_id, color_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sqlInsert);
		stmt.setInt(1, item.getCloth().getClothId());
		stmt.setFloat(2, (float) item.getPrice());
		stmt.setInt(3, item.getQuantity());
		stmt.setInt(4, newOrderId);
		stmt.setInt(5, item.getSize().getSizeID());
		stmt.setInt(6, item.getColor().getColor().getColorID());
		
		stmt.execute();
	}

	private void addOrderInfor(OrderInfo info, int newOrderId) throws SQLException {
		String SQL_INSERT = "INSERT INTO orderinfo(OrderID, Name, Email, PhoneNumber, Address, district, subDistrict, City, Note) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT);
		stmt.setInt(1, newOrderId);
		stmt.setString(2, info.getName());
		stmt.setString(3, info.getEmail());
		stmt.setString(4, info.getPhoneNumber());
		Address address = info.getAddress();
		stmt.setString(5, address.getStreet());
		stmt.setString(6, address.getDistrict());
		stmt.setString(7, address.getSubDistrict());
		stmt.setString(8, address.getCity());
		stmt.setString(9, "");
		
		stmt.execute();
		
	}

	private int initiateOrder(Order order) throws SQLException {
		String SQL_INSERT = "";
		if(order.getAccount() != null) {
			SQL_INSERT = "INSERT INTO orders(create_date, status_id, account_id) "
					+ "VALUES(?, ?, ?)";
		}
		else {
			SQL_INSERT = "INSERT INTO orders(create_date, status_id) "
					+ "VALUES(?, ?)";
		}
		
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		Date date = new Date();
		stmt.setString(1, date.toString());
		stmt.setInt(2, 1); // status default
		if(order.getAccount() != null) {
			stmt.setInt(3, order.getAccount().getAccoutnId());
		}
		
		int affectedRow = stmt.executeUpdate();
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if(generatedKeys.next()) {
			return generatedKeys.getInt(1);
		}
		return -1;
	}
	
	public static void main(String[] arg) {
	}
}
