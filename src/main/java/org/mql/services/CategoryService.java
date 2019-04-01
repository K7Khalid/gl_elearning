package org.mql.services;

import java.util.List;

import org.mql.models.Category;

public interface CategoryService {
	Category save(Category category);
	List<Category> findAll();
	boolean existsById(Integer id);
	Category findById(Integer id);
	List<Category> findTop6();
}
