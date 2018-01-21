package com.mickaelbrenoit.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mickaelbrenoit.business.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	Item findByTitle(String title);
	Item findByUniversalProductCode(Long upc);
	
	@Query("SELECT i FROM Item i WHERE i.category.nameCategory LIKE CONCAT('%',:nameCategory,'%')")
	List<Item> findAllByCategoryName(@Param("nameCategory") String nameCategory);
}
