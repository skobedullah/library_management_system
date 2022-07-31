package com.lsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsm.model.User;
import com.lsm.repository.UserRepository;

@Controller
@RequestMapping("/librarian")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@RequestMapping("/home")
	public String home() {
		return "/librarian/home";
	}
	
	
}
