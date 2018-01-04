package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.Category;

public interface CategoryService {
    Category save(Category entity);
    void delete(Long id);
    List<Category> findAll();
    Category findById(Long id);
    Category findByNameCategory(String nameCategory);
}
