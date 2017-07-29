package com.models;

public class PostTeamCreate {

	private String name;
	private String register_name;
	private long department_id;
	
	public PostTeamCreate() {}

	public PostTeamCreate(String name, String register_name, long department_id) {
		super();
		this.name = name;
		this.register_name = register_name;
		this.department_id = department_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegister_name() {
		return register_name;
	}

	public void setRegister_name(String register_name) {
		this.register_name = register_name;
	}

	public long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}

	
}
