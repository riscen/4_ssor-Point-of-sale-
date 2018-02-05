package Containers;

public class Transactions {

	private int id;
	private float total;
	private boolean isSell;
	
	public Transactions() {
		
	}
	
	public Transactions(int id, float total, boolean isSell) {
		setId(id);
		setTotal(total);
		setIsSell(isSell);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public void setIsSell(boolean isSell) {
		this.isSell = isSell;
	}
	
	public int getId() {
		return id;
	}
	
	public float getTotal() {
		return total;
	}
	
	public boolean isSell() {
		return isSell;
	}
}
