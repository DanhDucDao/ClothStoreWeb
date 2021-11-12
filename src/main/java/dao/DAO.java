package dao;

import java.sql.DriverManager;
import java.sql.Connection;

public class DAO {
	protected static Connection con = null;
	
	private static String DATABASE = "jdbc:mysql://localhost:3306/cloth_store_db";
	private static String USER = "root";
	private static String PASSWORD = "admin";
		
	public DAO() {
		if(con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(DATABASE, USER, PASSWORD);
				
			} catch (Exception ex) {
				//to do
			}	
		}
	}

}