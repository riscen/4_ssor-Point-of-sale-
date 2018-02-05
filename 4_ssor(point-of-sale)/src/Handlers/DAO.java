package Handlers;

import java.util.ArrayList;

public class DAO <T> {

	private ArrayList<T> list;
	
	public DAO() {
		list = new ArrayList<T>();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int getTotal() {
		return list.size();
	}
	
	public void add(T data) {
		list.add(data);
	}
	
	public boolean update(int index, T data) {
		if(isEmpty() || index >= getTotal())
			return false;
		list.set(index, data);
		return true;
	}
	
	public boolean remove(int index) {
		if(isEmpty() || index >= getTotal())
			return false;
		list.remove(index);
		return true;
	}
	
	public T get(int index) {
		if(isEmpty() || index >= getTotal())
			return null;
		return list.get(index);
	}
	
	public void clearList() {
		list.clear();
	}
}
