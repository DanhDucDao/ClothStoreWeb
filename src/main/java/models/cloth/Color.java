package models.cloth;

import java.io.Serializable;

public class Color implements Serializable{

	/**
	 * @author LAPTOP
	 */
	private static final long serialVersionUID = 3441549290746687952L;
	private int colorID;
	private String name;
	private String abbreviation;
	private int red;
	private int green;
	private int blue;
	
	
	public Color() {
		super();
	}


	public Color(int colorID, String name, String abbreviation, int red, int green, int blue) {
		super();
		this.colorID = colorID;
		this.name = name;
		this.abbreviation = abbreviation;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}


	public int getColorID() {
		return colorID;
	}


	public void setColorID(int colorID) {
		this.colorID = colorID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}


	public int getRed() {
		return red;
	}


	public void setRed(int red) {
		this.red = red;
	}


	public int getGreen() {
		return green;
	}


	public void setGreen(int green) {
		this.green = green;
	}


	public int getBlue() {
		return blue;
	}


	public void setBlue(int blue) {
		this.blue = blue;
	}
	
}

