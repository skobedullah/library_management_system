package com.lsm.service;

import java.util.List;

import com.lsm.model.Book;
import com.lsm.model.User;
import com.lsm.repository.BookRepository;

public class BookService implements BookRepository{

	@Override
	public User addBook(Book book) {
		// TODO Auto-generated method stub
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




































/*
 * @Autowired JdbcTemplate jdbcTemplate;
 * 
 * @Override public List<Employee> findAll() { return
 * jdbcTemplate.query("SELECT * FROM tbl_employees", new
 * BeanPropertyRowMapper<Employee>(Employee.class)); }
 * 
 * @Override public Employee findById(int id) { return
 * jdbcTemplate.queryForObject("SELECT * FROM tbl_employees WHERE id=?", new
 * BeanPropertyRowMapper<Employee>(Employee.class), id); }
 * 
 * @Override public int deleteById(int id) { return
 * jdbcTemplate.update("DELETE FROM tbl_employees WHERE id=?", id); }
 * 
 * @Override public int save(Employee e) { return jdbcTemplate.
 * update("INSERT INTO tbl_employees (name, location, department) VALUES (?, ?, ?)"
 * , new Object[] {e.getName(), e.getLocation(), e.getDepartment()}); }
 * 
 * @Override public int update(Employee e, int id) { return jdbcTemplate.
 * update("UPDATE tbl_employees SET name = ?, location = ?, department = ? WHERE id = ?"
 * , new Object[] {e.getName(), e.getLocation(), e.getDepartment(), id}); }
 */