package com.lsm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsm.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/admin_dashboard")
	public String adminHome(Model model, Principal principal) {
		model.addAttribute("title", "Admin DashBoard");
		return "/admin/adminDashboard";
	}

	@RequestMapping("/add_librarian")
	public String addLibrarian(User user,Model model, Principal principal) {

		model.addAttribute("title", "Add Librarian");
		return "admin/addLibrarian";
	}

}
