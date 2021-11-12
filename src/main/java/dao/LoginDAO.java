package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.order.Account;
public class LoginDAO extends DAO{
	public LoginDAO() {
		super();
	}
	
	public Account checkLogin(String username, String password) throws SQLException {
		String SQL = "SELECT * FROM accounts " 
				+ "WHERE username = ? AND password = ?;";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		ResultSet set = stmt.executeQuery();
		if(!set.next()) 
			return null;
		int accountId = set.getInt("account_id");
		String position = set.getString("position");
		String email = set.getString("email");
		String phoneNumber = set.getString("phone_number");
		Account account = new Account(accountId, username, password, position, email, phoneNumber);
		
		return account;
	}
}
