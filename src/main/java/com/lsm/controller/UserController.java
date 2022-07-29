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
		/*
		 * System.out.println("invokation start of adding a user"); User u1=new
		 * User(102,"sk obedullah",
		 * "obedullah001@gmail.com","obed@111",true,"admin",""); User addUser =
		 * repository.addUser(u1);
		 * if(addUser==null)System.out.println("user not added to db");
		 * System.out.println("user added sucessfully");
		 */
		
		return "/librarian/home";
	}
}
