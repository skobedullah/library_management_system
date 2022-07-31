package com.lsm.service;

import java.util.List;

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



	@Override
	public User getUserByuserName(String name) {
		String query="select * from user where email=?";
		jdbcTemplate.execute(query);
		return null;
	}



	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int update(User e, int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
