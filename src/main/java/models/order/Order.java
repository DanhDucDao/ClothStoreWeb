package models.order;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
	private static final long serialVersionUID = 7024295502464146046L;
	
	private int orderId;
	private OrderInfo info;
	private Account account;
	private ArrayList<LineItem> listItems;
	private Status status;
	
	public Order() {
		listItems = new ArrayList<LineItem>();
	}

	public Order(int orderId, OrderInfo info, Account account, ArrayList<LineItem> listItems, Status status) {
		super();
		this.orderId = orderId;
		this.info = info;
		this.account = account;
		this.listItems = listItems;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderInfo getInfo() {
		return info;
	}

	public void setInfo(OrderInfo info) {
		this.info = info;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<LineItem> getListItems() {
		return listItems;
	}

	public void setListItems(ArrayList<LineItem> listItems) {
		this.listItems = listItems;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
