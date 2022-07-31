package com.lsm.repository;


import java.util.List;

import com.lsm.model.User;




public interface UserRepository {
	
	public User addUser(User user);
	
	public User getUserByuserName(String name);
	
    public List<User> getAll();
	
	public User getById(int id);
	
	public int deleteById(int id);
	
	public int update(User e, int id);
	

}
