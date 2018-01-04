package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelbrenoit.business.model.TypeItem;

public interface TypeItemRepository extends JpaRepository<TypeItem, Long>{
	TypeItem findByNameItem(String nameItem);
}
