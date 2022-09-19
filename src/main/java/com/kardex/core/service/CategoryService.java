package com.kardex.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.core.entity.Category;
import com.kardex.core.exception.CategoryException;
import com.kardex.core.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	/**
	 * @param id
	 * @return
	 * @throws CategoryException
	 */
	public Category getCategoryById (Integer id) throws CategoryException {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryException("The category does not exist."));
	}
	
	/**
	 * @return
	 */
	public List<Category> getAllCategories () {
		return categoryRepository.findAll();
	}
}
