package models.cloth;

import java.io.Serializable;
import java.util.ArrayList;

public class Cloth implements Serializable{

	/**
	 * @author LAPTOP
	 */
	private static final long serialVersionUID = 1L;
	private int clothId;
	private String name;
	private String desciption;
	private String mainImageUrl;
	private ArrayList<ItemColor> avaiableColors;
	private ArrayList<Size> avaiableSizes;
	private double price;
	
	public Cloth() {
		super();
		avaiableColors = new ArrayList<>();
		avaiableSizes = new ArrayList<>();
	}
	
	public Cloth(int clothId, String name, String desciption, String mainImageUrl, ArrayList<ItemColor> avaiableColors,
			ArrayList<Size> avaiableSizes) {
		super();
		this.clothId = clothId;
		this.name = name;
		this.desciption = desciption;
		this.mainImageUrl = mainImageUrl;
		this.avaiableColors = avaiableColors;
		this.avaiableSizes = avaiableSizes;
	}

	public int getClothId() {
		return clothId;
	}
	public void setClothId(int clothId) {
		this.clothId = clothId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public ArrayList<ItemColor> getAvaiableColors() {
		return avaiableColors;
	}

	public void setAvaiableColors(ArrayList<ItemColor> avaiableColors) {
		this.avaiableColors = avaiableColors;
	}

	public ArrayList<Size> getAvaiableSizes() {
		return avaiableSizes;
	}

	public void setAvaiableSizes(ArrayList<Size> avaiableSizes) {
		this.avaiableSizes = avaiableSizes;
	}
	
	public void addSize(Size size) {
		this.avaiableSizes.add(size);
	}
	
	public void addColor(ItemColor color) {
		this.avaiableColors.add(color);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
}
