package models.cloth;

import java.io.Serializable;

public class Size implements Serializable{

	/**
	 * @author LAPTOP
	 */
	private static final long serialVersionUID = -1701173032719713091L;
	
	private int sizeID;
	private String name;
	
	public Size() {
		super();
	}

	public Size(int sizeID, String name) {
		super();
		this.sizeID = sizeID;
		this.name = name;
	}

	public int getSizeID() {
		return sizeID;
	}

	public void setSizeID(int sizeID) {
		this.sizeID = sizeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
