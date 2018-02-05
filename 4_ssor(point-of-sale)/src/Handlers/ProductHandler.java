package Handlers;

import Containers.Product;

public class ProductHandler {

	private DAO<Product> products;
	
	public ProductHandler() {
		products = new DAO<Product>();
	}
	
	public boolean isEmpty() {
		return products.isEmpty();
	}
	
	public void add(int id, String name, float price, int total) {
		products.add(new Product(id, name, price, total));
	}
	
	public boolean update(int index, Product p) {
		return products.update(index, p);
	}
	
	public Product search(int id) {
		for(int i = 0, j = products.getTotal(); i < j; i++) {
			if(id == products.get(i).getId())
				return products.get(i);
		}
		return null;
	}
	
	public boolean remove(int index) {
		return products.remove(index);
	}
	
	public Product get(int index) {
		return products.get(index);
	}
	
	public int getTotal() {
		return products.getTotal();
	}
	
	public void clear() {
		products.clearList();
	}
	
}
