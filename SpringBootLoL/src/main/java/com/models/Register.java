package com.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "register")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String username;
	@NotNull
	private String name;
	@NotNull
	private String workerid;
	@NotNull
	private String password;
	@NotNull
	private String mobile;
	@NotNull
	private String email;
	@NotNull
	private String position;
	
	public Register() {}

	public Register(long id) {
//		super();
		this.id = id;
	}

	public Register(String username, String name, String workerid, String password, String mobile, String email,
			String position) {
//		super();
		this.username = username;
		this.name = name;
		this.workerid = workerid;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkerid() {
		return workerid;
	}

	public void setWorkerid(String workerid) {
		this.workerid = workerid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Register [username=" + username + ", name=" + name + ", workerid=" + workerid + ", password=" + password
				+ ", mobile=" + mobile + ", email=" + email + ", position=" + position + "]";
	}
	
}
