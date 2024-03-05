package coder.models;

public class CartItem {
	private int id,count;
	public CartItem() {
		
	}
	public CartItem(int id, int count) {
		this.id = id;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
