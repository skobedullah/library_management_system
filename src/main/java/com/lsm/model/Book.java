package com.lsm.model;

import org.springframework.stereotype.Component;

@Component
public class Book {

	private int id;


	private String name;

	private String title;

	private String authors;

	private String description;

	public Book() {
		super();
	}

	public Book(int id, String name, String title, String authors, String description) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.authors = authors;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", title=" + title + ", authors=" + authors + ", description="
				+ description + "]";
	}

}
