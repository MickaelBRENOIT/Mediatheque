package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mickaelbrenoit.business.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	Item findByTitle(String title);
}
