package com.lsm.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsm.helper.Message;
import com.lsm.model.Book;
import com.lsm.model.User;
import com.lsm.service.BookService;
import com.lsm.service.UserService;

import com.razorpay.*;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

	/**
	 * Injecting BookService
	 */
	@Autowired
	private BookService bookService;

	
	/**
	 * Injecting UserService
	 */
	@Autowired
	private UserService userService;
	
	
	
	
	
	//creating order
	
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
		System.out.println("order creation function called");
		System.out.println(data);
		int amount=Integer.parseInt(data.get("amount").toString());
		
		RazorpayClient rpc = new RazorpayClient("rzp_test_MxDm4phYN6zBXl", "R2Bz374FMEqdjXNWl7zAM3QX");
		
		JSONObject options = new JSONObject();
		options.put("amount", amount*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		rpc.orders.create(options);
		System.out.println(rpc.orders.create(options).toString());
		return rpc.orders.create(options).toString();
	}
	
	
	
	
	

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
		System.out.println("c --common data aaded ---" + userName);
		User user = userService.getUserByuserName(userName);
		System.out.println("c --common data aaded ---user added to the model as a common data--" + user);
		model.addAttribute("cUser", user);
	}

	

	/**
	 * view librarianDashBoard
	 * 
	 * @Param Model
	 * @Return Return librarianDashBoard as an UI
	 * 
	 **/
	@RequestMapping("/librarian_dashboard")
	public String adminDashBoard(Model model) {
		model.addAttribute("title", "Librarian DashBoard");
		return "/librarian/librarianDashboard";
	}

	/**
	 * add Book Form
	 * 
	 * @Param Model
	 * @param Book
	 * @Return Redirect addBook form as an UI
	 * 
	 **/

	@RequestMapping("/add_book")
	public String addBook(Book book, Model model) {

		model.addAttribute("title", "add book");
		model.addAttribute("book", new Book());
		return "/librarian/addBook";
	}

	/**
	 * Process add Book Form
	 * 
	 * @Param Model
	 * @param Session
	 * @Param Valid
	 * @param User
	 * @param BindingResult
	 * @Return Return addBook as an UI
	 * 
	 **/

	@PostMapping("/processform")
	public String processAddbookForm(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model,
			HttpSession session) {

		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("book", book);
				return "librarian/addBook";
			}
			Book result = bookService.addBook(book);
			model.addAttribute("book", new Book());
			session.setAttribute("message", new Message("Book Successfully Added", "alert-success"));
			return "librarian/addBook";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("opps!! Something went wrong" + e.getMessage(), "alert-danger"));
			return "librarian/addBook";
		}
	}

	/**
	 * View Books
	 * 
	 * @Param Model
	 * @Return All added Books
	 * 
	 **/

	@RequestMapping("/view_books")
	public String viewBooks(Model model) {

		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "/librarian/viewBooks";
	}

	/**
	 * View Book
	 * 
	 * @Param Model
	 * @Param Book ID
	 * @Return viewBook page
	 * 
	 **/

	@RequestMapping("/view_book/{id}")
	public String viewBook(@PathVariable("id") int id, Model model) {

		Book book = bookService.getById(id);
		model.addAttribute("book", book);

		return "librarian/viewBook";
	}

	/**
	 * Book Update Form
	 * 
	 * @param Book  id
	 * @param Model
	 * @Return Return Book Update Form as an UI
	 * 
	 **/
	@RequestMapping("/update_book/{id}")
	public String updateBook(@PathVariable("id") int id, Model model) {
		Book retriveBook = bookService.getById(id);
		model.addAttribute("book", retriveBook);
		return "/librarian/updateBook";
	}

	/**
	 * Process Book Update Form
	 * 
	 * @param Book
	 * @Return Redirecting to the View Books page
	 * 
	 **/
	@PostMapping("/process_updateform")
	public String processUpdateBookForm(@ModelAttribute("book") Book book) {

		bookService.update(book, book.getId());
		return "redirect:/librarian/view_books";
	}

	/**
	 * Delete Book
	 * 
	 * @param Model
	 * @param Book  ID
	 * @Return Redirecting to the View Books page
	 * 
	 **/
	@RequestMapping("/delete_book/{id}")
	public String deletebook(@PathVariable("id") int id, Model model) {
		boolean deleteById = bookService.deleteById(id);
		return "redirect:/librarian/view_books";
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
		System.out.println("view profile method invoked");
		String userName = principal.getName();
		System.out.println("current loggedin user----" + userName);
		User user = userService.getUserByuserName(userName);

		httpSession.setAttribute("user", user);
		model.addAttribute("user", user);
		model.addAttribute("title", "Admin Profile");
		System.out.println(user);
		return "/librarian/viewProfile";
	}

}
