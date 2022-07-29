package com.lsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lsm.model.User;
import com.lsm.repository.UserRepository;

@Service
public class UserService implements UserRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	@Override
	public User addUser(User u1) {
		String query="insert into user(id,name,email,password,enabled,role,image_url) values(?,?,?,?,?,?,?)";
		int update = jdbcTemplate.update(query, u1.getId(),u1.getName(),u1.getEmail(),u1.getPassword(),u1.isEnabled(),u1.getRole(),u1.getImageUrl());
		if(update>0)return u1;
		return null;
	}
	
	

}
