package Containers;

public class Product {

	private int id;
	private String name;
	private float price;
	private int total;
	
	public Product() {
		
	}
	
	public Product(int id, String name, float price, int total) {
		setId(id);
		setName(name);
		setPrice(price);
		setTotal(total);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getTotal() {
		return total;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.valueOf(getId())).append(" - ").append(getName()).append(" - ").
		append(String.valueOf(getPrice())).append(" - ").append(String.valueOf(getTotal()));
		return s.toString();
	}
}
