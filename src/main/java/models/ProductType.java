package models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductType implements Serializable{

	/**
	 * @author DanhKuto
	 */
	private static final long serialVersionUID = 1L;
	
	private int productTypeID;
	private String name;
	private ArrayList<Category> listCategories;
	
	public ProductType() {
		super();
		listCategories = new ArrayList<>();
	}

	public ProductType(int productTypeID, String name, ArrayList<Category> listCategories) {
		super();
		this.productTypeID = productTypeID;
		this.name = name;
		this.listCategories = listCategories;
	}

	public int getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(int productTypeID) {
		this.productTypeID = productTypeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<Category> getListCategories() {
		return listCategories;
	}

	public void setListCategories(ArrayList<Category> listCategories) {
		this.listCategories = listCategories;
	}
	
}
