package com.cisco.cdr.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

	@Id
    public String id;
	
	private RoleName name;

	public Role() {

	}

	public Role(RoleName name) {
		this.name = name;
	}

	public String get_Id() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
}