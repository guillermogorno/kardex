package com.kardex.core.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.core.dto.CartDTO;
import com.kardex.core.dto.ProductDTO;
import com.kardex.core.entity.Product;
import com.kardex.core.exception.ProductException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
	
	@Autowired
	private ProductService productService;

	
	/**
	 * @param cartDTO
	 * @return
	 */
	public CartDTO sellProducts (CartDTO cartDTO) {		
		List<ProductDTO> productDTOs = cartDTO.getProducts();		
		return concreteSellingOperation(productDTOs);
	}
	
	/**
	 * @param productDTOs
	 * @return
	 */
	private CartDTO concreteSellingOperation (List<ProductDTO> productDTOs) {
		Set<Integer> productIds = productDTOs.stream().filter(productDTO -> productDTO.isStockAvailable())
				.map(productDTO -> {
					Product product = null;
					try {
						product = productService.decrementStock(productDTO.getProductId(), productDTO.getAmount());
					} catch (ProductException e) {
						log.error("Error : " + e.getMessage());
					}
					return product.getId();
				}).collect(Collectors.toSet());
		

		List<ProductDTO> soldOut = productDTOs.stream()
				.filter(productDTO -> productIds.contains(productDTO.getProductId())).toList();
		
		return toCartDTO(soldOut);
	}
	
	/**
	 * @param productDTOs
	 * @return
	 */
	private CartDTO toCartDTO (List<ProductDTO> productDTOs) {
		return new CartDTO(productDTOs);
	}
	
}
