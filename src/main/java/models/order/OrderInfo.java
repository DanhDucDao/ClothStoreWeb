package models.order;

import java.io.Serializable;

public class OrderInfo implements Serializable{

	private static final long serialVersionUID = 1692993827409392974L;
	
	private int infoId;
	private String name;
	private String email;
	private String phoneNumber;
	private Address address;
	
	public OrderInfo() {
		
	}

	public OrderInfo(int infoId, String name, String email, String phoneNumber, Address address) {
		super();
		this.infoId = infoId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
