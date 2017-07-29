package com.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String group_id;
	
	@NotNull
	private String name;
	
	@ManyToOne
	private Department department;

	@OneToOne
	private Register register;
	
	public Team() {}
	
	

	public Team(long id) {
//		super();
		this.id = id;
	}



	public Team(String group_id, String name, Department department, Register register) {
//		super();
		this.group_id = group_id;
		this.name = name;
		this.department = department;
		this.register = register;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public String getGroup_id() {
		return group_id;
	}



	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



	public void setName(String name) {
		this.name = name;
	}






	public Department getDepartment() {
		return department;
	}



	public void setDepartment(Department department) {
		this.department = department;
	}



	public Register getRegister() {
		return register;
	}



	public void setRegister(Register register) {
		this.register = register;
	}




	
	
}
