package Handlers;

import Containers.Transactions;

public class TransactionHandler {

	private DAO<Transactions> transactions;
	
	public TransactionHandler() {
		transactions = new DAO<Transactions>();
	}
	
	public boolean isEmpty() {
		return transactions.isEmpty();
	}
	
	public void add(int id, float total, boolean isSell) {
		transactions.add(new Transactions(id, total, isSell));
	}
	
	public boolean update(int index, Transactions p) {
		return transactions.update(index, p);
	}
	
	public boolean remove(int index) {
		return transactions.remove(index);
	}
	
	public Transactions get(int index) {
		return transactions.get(index);
	}
	
	public int getTotal() {
		return transactions.getTotal();
	}
	
	public void clear() {
		transactions.clearList();
	}
}
