package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.Item;

public interface ItemService {
    Item save(Item entity);
    void delete(Long id);
    List<Item> findAll();
    Item findById(Long id);
    Item findByTitle(String title);
}
