package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelbrenoit.business.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByNameCategory(String nameCategory);
}
