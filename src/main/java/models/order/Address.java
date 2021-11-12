package models.order;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String street;
	private String subDistrict;
	private String district;
	private String city;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String subDistrict, String district, String city) {
		super();
		this.street = street;
		this.subDistrict = subDistrict;
		this.district = district;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
