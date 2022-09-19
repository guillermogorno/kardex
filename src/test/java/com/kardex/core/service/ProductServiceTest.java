package com.kardex.core.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kardex.core.entity.Product;
import com.kardex.core.exception.ProductException;
import com.kardex.core.repository.ProductRepository;
import com.kardex.core.util.JsonTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository productRepository;

	@Test
	public void when_incrementStock_with_validProduct_and_validAmount_then_ok() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);		
		service.incrementStock(product.getId(), 11);		
		verify(productRepository, times(1)).save(product);		
	}
	
	@Test(expected = ProductException.class)
	public void when_incrementStock_with_validProduct_and_invalidAmount_then_exception() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);		
		service.incrementStock(product.getId(), -11);		
	}
	
	@Test(expected = ProductException.class)
	public void when_incrementStock_with_invalidProduct_and_validAmount_then_exception() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.FALSE);		
		service.incrementStock(1, 11);		
	}
	
	@Test
	public void when_decrementStock_with_validAmount_and_validProduct_then_ok() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);
		service.decrementStock(product.getId(), 2);
		verify(productRepository, times(1)).save(product);
	}
	
	@Test(expected = ProductException.class)
	public void when_decrementStock_with_invalidAmount_and_validProduct_then_ok() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);
		service.decrementStock(product.getId(), -2);
		verify(productRepository, times(0)).save(product);
	}
	
	@Test(expected = ProductException.class)
	public void when_decrementStock_with_validAmount_and_invalidProduct_then_ok() throws ProductException {
		Product product = JsonTestUtils.getInstance().deserializeJsonFile("single-product.json",Product.class);
		when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
		when(productRepository.existsById(any())).thenReturn(Boolean.FALSE);
		service.decrementStock(product.getId(), 2);
		verify(productRepository, times(0)).save(product);
	}
	
}
