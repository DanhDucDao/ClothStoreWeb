package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.order.Account;

public class ProfileDAO extends DAO{
	public void updateProfile(Account account) throws SQLException {
		String SQL = "UPDATE accounts "
				+ "SET password = ?, phone_number = ?, email = ? "
				+ "WHERE account_id = ?";
		
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, account.getPassword());
		stmt.setString(2, account.getPhoneNumber());
		stmt.setString(3, account.getEmail());
		stmt.setInt(4, account.getAccoutnId());
		
		stmt.execute();
	}
	
	public String addProfile(Account account) throws SQLException {
		String SQL = "SELECT * FROM accounts WHERE username = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, account.getUsername());
		ResultSet set = stmt.executeQuery();
		if(set.next())
			return "usernameReplicate";
		
		SQL = "INSERT INTO accounts(username, password, email, phone_number, position) "
				+ "VALUES(?, ?, ?, ?, ?);";
		stmt = con.prepareStatement(SQL);
		stmt.setString(1, account.getUsername());
		stmt.setString(2, account.getPassword());
		stmt.setString(3, account.getEmail());
		stmt.setString(4, account.getPhoneNumber());
		stmt.setString(5, "customer");
		stmt.execute();
		
		return "OK";
	}
}
