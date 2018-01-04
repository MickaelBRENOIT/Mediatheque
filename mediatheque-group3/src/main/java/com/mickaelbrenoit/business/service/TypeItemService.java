package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.TypeItem;

public interface TypeItemService {
    TypeItem save(TypeItem entity);
    void delete(Long id);
    List<TypeItem> findAll();
    TypeItem findById(Long id);
    TypeItem findByNameItem(String nameItem);
}
