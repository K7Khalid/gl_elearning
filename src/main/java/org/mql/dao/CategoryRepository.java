package org.mql.dao;

import java.util.List;

import org.mql.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findTop6ByOrderById();
}
