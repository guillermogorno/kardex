package com.kardex.core.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.core.dto.InventoryDTO;
import com.kardex.core.dto.ProductDTO;
import com.kardex.core.entity.Product;
import com.kardex.core.exception.ProductException;
import com.kardex.core.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * @param id
	 * @param amount
	 * @return
	 * @throws ProductException
	 */
	public Product incrementStock (Integer id, Integer amount) throws ProductException {		
		validateProduct (id, amount);		
		Product product = productRepository.findById(id).get();		
		Integer newStock = product.getStock() + amount; 
		product.setStock(newStock);
		
		return productRepository.save(product);
			
	}
	
	/**
	 * @param id
	 * @param amount
	 * @return
	 * @throws ProductException
	 */
	public Product decrementStock (Integer id, Integer amount) throws ProductException {		
		validateProduct (id, amount);		
		Product product = productRepository.findById(id).get();		
		if (product.getStock() < amount) {
			throw new ProductException ("Invalid Amount : " + amount);
		}		
		Integer newStock = product.getStock() - amount;		
		product.setStock(newStock);
		
		return productRepository.save(product);
	}
	
	/**
	 * @param inventoryDTO
	 * @return
	 */
	public List<Product> getProductsByInventoryDTO (InventoryDTO inventoryDTO) {
		List<ProductDTO> productDTOs = inventoryDTO.getProducts();
		
		Set<Integer> ids = productDTOs.stream().map(productDTO -> productDTO.getProductId())
				.collect(Collectors.toSet());
		
		return productRepository.findAllById(ids).stream()
				.filter(p -> p.getCategory().getId() == inventoryDTO.getCategoryId()).collect(Collectors.toList());
	}
	
	/**
	 * @param id
	 * @param amount
	 * @throws ProductException
	 */
	private void validateProduct (Integer id, Integer amount) throws ProductException {		
		if (!isValidAmount (amount)) {
			throw new ProductException ("This product amount " + amount + " is invalid.");
		}		
		if (!isThisProductExists (id)) {
			throw new ProductException ("This product "+ id +" doesn't exist.");
		}		
	}
		
	/**
	 * @param amount
	 * @return
	 */
	private boolean isValidAmount (Integer amount) {
		return null != amount && amount > 0;
	}
	
	/**
	 * @param id
	 * @return
	 */
	private boolean isThisProductExists (Integer id) {
		return null != id && productRepository.existsById(id);
	}
	
}
