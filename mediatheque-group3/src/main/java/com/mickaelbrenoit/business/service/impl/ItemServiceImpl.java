package com.mickaelbrenoit.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.model.Item;
import com.mickaelbrenoit.business.repository.ItemRepository;
import com.mickaelbrenoit.business.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public Item save(Item entity) {
		return itemRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		itemRepository.delete(id);
	}

	@Override
	public List<Item> findAll() {
		return (List<Item>) itemRepository.findAll();
	}

	@Override
	public Item findById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Item findByTitle(String title) {
		return itemRepository.findByTitle(title);
	}

	@Override
	public Item findByUniversalProductCode(Long upc) {
		return itemRepository.findByUniversalProductCode(upc);
	}

}
