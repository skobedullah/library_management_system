package com.lsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsm.model.User;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	//test
	@RequestMapping("/home")
	public String adminHome() {
		return "/admin/home";
	}
	public String addUser(User user) {
		
		return "";
	}

}
