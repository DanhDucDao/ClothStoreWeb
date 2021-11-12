package models.cloth;

import java.io.Serializable;

public class ItemColor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Color color;
	private String imageUrl;
	
	public ItemColor() {
		
	}

	public ItemColor(Color color, String imageUrl) {
		super();
		this.color = color;
		this.imageUrl = imageUrl;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color colorId) {
		this.color = colorId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
