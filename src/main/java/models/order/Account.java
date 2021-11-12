package models.order;

import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4136221404776838008L;

	private int accoutnId;
	private String username;
	private String password;
	private String position;
	private String email;
	private String phoneNumber;
	
	public Account() {
		
	}

	public Account(int accoutnId, String username, String password, String position, String email, String phoneNumber) {
		super();
		this.accoutnId = accoutnId;
		this.username = username;
		this.password = password;
		this.position = position;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getAccoutnId() {
		return accoutnId;
	}

	public void setAccoutnId(int accoutnId) {
		this.accoutnId = accoutnId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
