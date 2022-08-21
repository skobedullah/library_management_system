package com.lsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lsm.model.User;
import com.lsm.repository.UserRepository;

import ch.qos.logback.core.joran.action.ActionUtil.Scope;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		User retriveUser = userRepository.addUser(user);
		return retriveUser;
	}

	public User getUserByuserName(String email) {
		User userByuserName = userRepository.getUserByuserName(email);
		return userByuserName;
	}

	public List<User> getAll() {

		List<User> all = userRepository.getAll();
		return all;
	}

	public User getById(int id) {

		User user = userRepository.getById(id);
		return user;

	}

	public boolean deleteById(int id) {

		boolean deleteById = userRepository.deleteById(id);
		return deleteById;
	}

	public boolean update(User e, int id) {

		boolean update = userRepository.update(e, id);
		return update;
	}

}
