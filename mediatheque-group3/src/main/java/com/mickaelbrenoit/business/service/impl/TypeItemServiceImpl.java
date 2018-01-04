package com.mickaelbrenoit.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.model.TypeItem;
import com.mickaelbrenoit.business.repository.TypeItemRepository;
import com.mickaelbrenoit.business.service.TypeItemService;

@Service
public class TypeItemServiceImpl implements TypeItemService {
	
	@Autowired
	private TypeItemRepository typeItemRepository;

	@Override
	public TypeItem save(TypeItem entity) {
		return typeItemRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		typeItemRepository.delete(id);
	}

	@Override
	public List<TypeItem> findAll() {
		return (List<TypeItem>) typeItemRepository.findAll();
	}

	@Override
	public TypeItem findById(Long id) {
		return typeItemRepository.findOne(id);
	}

	@Override
	public TypeItem findByNameItem(String nameItem) {
		return typeItemRepository.findByNameItem(nameItem);
	}

}
