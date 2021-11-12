package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

	/**
	 * @author Danh
	 */
	private static final long serialVersionUID = 1L;
	
	private int categoryID;
	private String name;
	private ArrayList<Category> childCategories;
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public Category(int categoryID, String name, ArrayList<Category> childCategories) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.childCategories = childCategories;
	}
	
	public Category() {
		super();
		childCategories = new ArrayList<>();
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Category> getChildCategories() {
		return childCategories;
	}
	public void setChildCategories(ArrayList<Category> childCategories) {
		this.childCategories = childCategories;
	}
	
	
}
