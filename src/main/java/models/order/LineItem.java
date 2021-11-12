package models.order;

import java.io.Serializable;
import models.cloth.*;

public class LineItem implements Serializable{
	
	private static final long serialVersionUID = -4552824441546687101L;
	private Cloth cloth;
	private ItemColor color;
	private Size size;
	private double price;
	private int quantity;
	
	public LineItem(Cloth cloth, ItemColor color, Size size, double price, int quantity) {
		super();
		this.cloth = cloth;
		this.color = color;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
	}
	
	public LineItem() {
		
	}

	public Cloth getCloth() {
		return cloth;
	}

	public void setCloth(Cloth cloth) {
		this.cloth = cloth;
	}

	public ItemColor getColor() {
		return color;
	}

	public void setColor(ItemColor color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
