package com.lsm.repository;

import org.springframework.stereotype.Repository;

import com.lsm.model.User;

public interface UserRepository {
	
	public User addUser(User user);
	

}
