package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.Role;

public interface RoleService {
	Role save(Role entity);
    void delete(Long idRole);
    List<Role> findAll();
    Role findById(Long idRole);
    Role findByName(String name); 
}
