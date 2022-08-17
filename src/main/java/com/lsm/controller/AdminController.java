package com.lsm.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.lsm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	/**
	 * Injecting PasswordEncoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * Injecting UserService
	 */
	@Autowired
	private UserService userService;

	
	/**
	 * Adding common data 
	 * 
	 * @Param Model
	 * @param Principal
	 * @Return Model object containing current loggedIn User Object 
	 * 
	 **/
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("common data aaded ---" + userName);
		User user = userService.getUserByuserName(userName);
		System.out.println("user added to the model as a common data--" + user);
		model.addAttribute("cUser", user);
	}
	
	
	/**
	 * view adminDashboard
	 * 
	 * @Param Model
	 * @Return Return adminDashBoard as an UI
	 * 
	 **/

	@RequestMapping("/admin_dashboard")
	public String adminDashBoard(Model model) {
		model.addAttribute("title", "Admin DashBoard");
		return "/admin/adminDashboard";
	}
 
	/**
	 * Librarian Form
	 * 
	 * @Param Model
	 * @param User 
	 * @Return Redirect addLibrarian as an UI
	 * 
	 **/
	
	@RequestMapping("/add_librarian")
	public String addLibrarian(User user, Model model) {
		model.addAttribute("title", "Add Librarian");
		model.addAttribute("user", new User());
		return "admin/addLibrarian";
	}

	/**
	 * Process add Librarian Form
	 * 
	 * @Param Model 
	 * @param Session
	 * @Param Valid
	 * @param User 
	 * @param BindingResult 
	 * @Return Return addLibrarian  as an UI
	 * 
	 **/
	
	
	@PostMapping("/processform")
	public String registerLibrarian(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,
			HttpSession session) {

		// System.out.println("process form invoked");
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				// System.out.println("has some error--in try if");
				return "admin/addLibrarian";
			}
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println();

			User result = userService.addUser(user);
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

	
	/**
	 * View Librarians
	 * 
	 * @Param Model 
	 * @Return All added Librarians
	 * 
	 **/
	
	@RequestMapping("/view_librarians")
	public String viewLibrarians(Model model) {
		model.addAttribute("title", "view Librarians");
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "admin/viewLibrarians";
	}

	
	
	/**
	 * View Librarian
	 * 
	 * @Param Model 
	 * @Param USER ID
	 * @Return viewLibrarian page 
	 * 
	 **/
	@GetMapping("/view_librarian/{id}")
	public String viewLibrarian(@PathVariable("id") int id, Model model) {
		System.out.println("viw Librarian executed");
		model.addAttribute("userId", id);
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "admin/viewLibrarian";
	}
	
	
	
	/**
	 * Librarian Update Form
	 * 
	 * @param User id
	 * @param Model 
	 * @Return Return Librarian Update Form  as an UI
	 * 
	 **/
	
	@PostMapping("/update_librarian/{id}")
	public String updateLibrarian(@PathVariable("id") int id, Model model) {
		User retriveUser = userService.getById(id);
		model.addAttribute("librarian", retriveUser);
		return "/admin/updateLibrarian";
	}
	

	/**
	 *  Process Librarian Update Form
	 * 
	 * @param User
	 * @Return Redirecting to the View Librarian page
	 * 
	 **/
	
	@PostMapping("/process_updateform")
	public String processUpdateLibrarianForm(@ModelAttribute("librarian") User user) {
		System.out.println("controller -----id  " + user.getId());
		userService.update(user, user.getId());
		System.out.println(user);
		return "redirect:/admin/view_librarians";
	}

	
	/**
	 *  Delete Librarian
	 * 
	 * @param Model
	 * @param USER ID 
	 * @Return Redirecting to the View Librarians page
	 * 
	 **/

	@GetMapping("/delete_librarian/{id}")
	public String deleteLibrarian(@PathVariable("id") int id, Model model) {
		if (userService.deleteById(id)) {
			model.addAttribute("message", new Message("librarian deleted successfully", "success"));
			return "redirect:/admin/view_librarians";
		}
		model.addAttribute("message", new Message("someting went wrong!!", "danger"));
		return "redirect:/admin/view_librarians";
	}


	/**
	 * view Current LoggedIn user Profile 
	 * 
	 * @param Model
	 * @param Principal 
	 * @param HttpSession 
	 * @Return loggedIn User Profile 
	 * 
	 **/
	@RequestMapping("/profile")
	public String viewProfile(Model model, Principal principal, HttpSession httpSession) {
		String userName = principal.getName();
		User user = userService.getUserByuserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "Admin Profile");
		System.out.println(user);
		return "/admin/viewProfile";
	}

}
