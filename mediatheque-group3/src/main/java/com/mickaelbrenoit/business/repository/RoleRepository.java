package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelbrenoit.business.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
