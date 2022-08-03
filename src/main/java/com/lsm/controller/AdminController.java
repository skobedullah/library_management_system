package com.lsm.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lsm.helper.Message;
import com.lsm.model.User;
import com.lsm.service.DummyUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	DummyUserService dummyUserService;

	@RequestMapping("/admin_dashboard")
	public String adminHome(Model model, Principal principal) {
		model.addAttribute("title", "Admin DashBoard");
		return "/admin/adminDashboard";
	}

	@RequestMapping("/add_librarian")
	public String addLibrarian(User user, Model model, Principal principal) {

		model.addAttribute("title", "Add Librarian");
		model.addAttribute("user", new User());
		return "admin/addLibrarian";
	}

	@PostMapping("/processform")
	public String registerLibrarian(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,
			HttpSession session) {

		// System.out.println("process form inviked");
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				// System.out.println("has some error--in try if");
				return "admin/addLibrarian";
			}
			User result = dummyUserService.addUser(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Librarian Successfully Registered", "alert-success"));
			return "admin/addLibrarian";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("opps!! Something went wrong" + e.getMessage(), "alert-danger"));
			return "admin/addLibrarian";
		}

	}
	
	@PostMapping("/process_updateform")
	public String processUpdateLibrarianForm(@ModelAttribute("librarian") User user) {
		
		System.out.println("controller -----id  "+user.getId());
		
		dummyUserService.update(user, user.getId());
		
		return "redirect:/admin/view_librarians";
	}

	@RequestMapping("/view_librarians")
	public String showLibrarians(Model model) {
		model.addAttribute("title", "view Librarians");

		List<User> users = dummyUserService.getAll();
		model.addAttribute("users", users);

		return "admin/viewLibrarians";
	}

	@GetMapping("/view_librarian/{id}")
	public String viewLibrarian(@PathVariable("id") int id, Model model) {

		System.out.println("viw Librarian executed");
		model.addAttribute("userId", id);
		User user = dummyUserService.getById(id);
		model.addAttribute("user", user);

		return "admin/viewLibrarian";
	}

	@GetMapping("/delete_librarian/{id}")
	public String deleteLibrarian(@PathVariable("id") int id, Model model, HttpSession session) {

		if(dummyUserService.deleteById(id)) {
			model.addAttribute("message", new Message("librarian deleted successfully", "success"));
			return "redirect:/admin/view_librarians";
		}

		model.addAttribute("message", new Message("someting went wrong!!", "danger"));
		return "redirect:/admin/view_librarians";
	}
	
	@PostMapping("/update_librarian/{id}")
	public String updateLibrarian(@PathVariable("id") int id, Model model,HttpSession session) {
		
		User retriveUser = dummyUserService.getById(id);
		model.addAttribute("librarian", retriveUser);
		return "/admin/updateLibrarian";
	}

}
