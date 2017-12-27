package com.mickaelbrenoit.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.model.Role;
import com.mickaelbrenoit.business.repository.RoleRepository;
import com.mickaelbrenoit.business.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public void delete(Long idRole) {
		roleRepository.delete(idRole);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long idRole) {
		return roleRepository.findOne(idRole);
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
