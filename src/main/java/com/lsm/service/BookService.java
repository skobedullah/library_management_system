package com.lsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lsm.model.Book;
import com.lsm.repository.BookRepository;
import com.lsm.repository.repositoryimplementation.BookRepositoryImpl;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book addBook(Book book) {

		Book addBook = bookRepository.addBook(book);

		return addBook;
	}

	public List<Book> getAll() {
		List<Book> books = bookRepository.getAll();

		return books;
	}

	public Book getById(int id) {
		Book book = bookRepository.getById(id);
		return book;
	}

	public boolean deleteById(int id) {
		boolean deleteById = bookRepository.deleteById(id);
		return deleteById;
	}

	public boolean update(Book e, int id) {
		boolean deleteById = bookRepository.update(e,id);
		return deleteById;
	}

}
