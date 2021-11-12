package models.order;

import java.io.Serializable;

public class Status implements Serializable{

	private static final long serialVersionUID = -4216899626468973211L;
	
	private int status_id;
	private String name;
	private String description;
	private String abbreviation;
	private String type;
	
	public Status() {
		
	}
	public Status(int status_id, String name, String description, String abbreviation, String type) {
		super();
		this.status_id = status_id;
		this.name = name;
		this.description = description;
		this.abbreviation = abbreviation;
		this.type = type;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
