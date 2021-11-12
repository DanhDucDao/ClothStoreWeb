package paging;

import models.cloth.*;

public class SearchFilter {
	private Color color;
	private Size size;
	private double maxPrice;
	private double minPrice;
	
	public SearchFilter(Color color, Size size, float maxPrice, float minPrice) {
		super();
		this.color = color;
		this.size = size;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
	}
	
	public SearchFilter() {
		this.maxPrice = -1;
		this.minPrice = -1;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	
	
}
