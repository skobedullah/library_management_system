package com.lsm.repository;

import java.util.List;

import com.lsm.model.Book;
import com.lsm.model.User;

public interface BookRepository {

public User addBook(Book book);
	
    public List<User> getAll();
	
	public User getById(int id);
	
	public int deleteById(int id);
	
	public int update(User e, int id);

}
