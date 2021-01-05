package com.training.ifaces;

import java.util.List;

public interface DataAccess<T> {
	public int add(T t);
	public int update(T ex,T update);
	public int remove(int mobileNum);
	public void addContactFromFile(String f);
	//public List<T> getNameAndEmail();
}
