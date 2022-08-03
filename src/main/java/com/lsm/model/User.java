package com.lsm.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class User {

	private int id;

	@NotBlank(message = "name is required")
	@Size(min = 3,max = 20,message = "min 3 and max 50 characters are aloowed")
	private String name;

	private String email;

	private String password;

	private boolean enabled;

	private String role;

	private String imageUrl;

	private String about;

	public User(int id, String name, String email, String password, boolean enabled, String role, String imageUrl,
			String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.imageUrl = imageUrl;
		this.about = about;
	}

	public User() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", enabled="
				+ enabled + ", role=" + role + ", imageUrl=" + imageUrl + ", about=" + about + "]";
	}

}
