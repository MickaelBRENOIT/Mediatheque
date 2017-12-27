package com.mickaelbrenoit.business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRole;

	@NotNull
	@Size(min = 2, max = 10)
	private String name;
	
	// This field doesn't exist in the database
    // It is simulated with a SQL query
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	private List<User> users = new ArrayList<>(0);
	
	public Role() {
	}
	
	public Role(String name) {
		this.name = name;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", name=" + name + ", users=" + users + "]";
	}

}
