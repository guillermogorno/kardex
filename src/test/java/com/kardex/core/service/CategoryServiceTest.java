package com.kardex.core.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kardex.core.entity.Category;
import com.kardex.core.entity.Product;
import com.kardex.core.exception.CategoryException;
import com.kardex.core.repository.CategoryRepository;
import com.kardex.core.util.JsonTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService service;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Test
	public void when_getCategoryById_with_validId_then_ok() throws CategoryException {
		Category category = JsonTestUtils.getInstance().deserializeJsonFile("single-category.json",Category.class);
		List<Product> products = JsonTestUtils.getInstance().deserializeJsonFile("products.json", new TypeReference<List<Product>>() {});
		category.setProducts(products);
		when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(category));
		Category c = service.getCategoryById(any());
		assertTrue(c != null);		
	}
	
	@Test(expected = CategoryException.class)
	public void when_getCategoryById_with_invalidId_then_ok() throws CategoryException {
		Category category = JsonTestUtils.getInstance().deserializeJsonFile("single-category.json",Category.class);
		List<Product> products = JsonTestUtils.getInstance().deserializeJsonFile("products.json", new TypeReference<List<Product>>() {});
		category.setProducts(products);
		when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(null));
		service.getCategoryById(any());
	}
	
	
	
}
