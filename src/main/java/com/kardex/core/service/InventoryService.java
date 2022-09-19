package com.kardex.core.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.core.dto.InventoryDTO;
import com.kardex.core.dto.ProductDTO;
import com.kardex.core.entity.Category;
import com.kardex.core.entity.Product;
import com.kardex.core.exception.CategoryException;
import com.kardex.core.exception.ProductException;
import com.kardex.core.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryService {

	private static final Integer ZERO = 0;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductService productService;

	/**
	 * @return
	 */
	public List<InventoryDTO> getAllInventory() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> toInventoryDTO(category)).collect(Collectors.toList());
	}

	/**
	 * @param categoryId
	 * @return
	 * @throws CategoryException
	 */
	public InventoryDTO getInventoryByCategory(Integer categoryId) throws CategoryException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryException("The category does not exist."));
		return toInventoryDTO(category);
	}

	/**
	 * @param inventoryDTO
	 * @return
	 */
	public InventoryDTO increaseProductStock(InventoryDTO inventoryDTO) {
		List<Product> products = productService.getProductsByInventoryDTO(inventoryDTO);
		Set<Integer> productDTOs = toProductDTO(products).stream().map(p -> p.getProductId())
				.collect(Collectors.toSet());
		List<ProductDTO> result = inventoryDTO.getProducts().stream()
				.filter(productDTO -> productDTOs.contains(productDTO.getProductId())).map(productDTO -> {
					ProductDTO p = null;
					try {
						Product product = productService.incrementStock(productDTO.getProductId(),
								productDTO.getAmount());
						p = ProductDTO.builder().amount(productDTO.getAmount()).productId(product.getId())
								.productName(product.getName()).stock(product.getStock()).build();
					} catch (ProductException e) {
						log.error("Error : " + e.getMessage());
					}
					return p;
				}).collect(Collectors.toList());

		return toInventoryDTO(inventoryDTO.getCategoryId(), inventoryDTO.getCategoryName(), result);
	}

	/**
	 * @param categoryId
	 * @param categoryName
	 * @param productDTOs
	 * @return
	 */
	private InventoryDTO toInventoryDTO(Integer categoryId, String categoryName, List<ProductDTO> productDTOs) {
		return new InventoryDTO(categoryId, categoryName, productDTOs);
	}

	/**
	 * @param category
	 * @return
	 */
	private InventoryDTO toInventoryDTO(Category category) {
		return InventoryDTO.builder().categoryId(category.getId()).categoryName(category.getName())
				.products(toProductDTO(category.getProducts())).build();
	}

	/**
	 * @param products
	 * @return
	 */
	private List<ProductDTO> toProductDTO(List<Product> products) {
		return products.stream().map(product -> ProductDTO.builder().productId(product.getId())
				.productName(product.getName()).stock(product.getStock()).amount(ZERO).build())
				.collect(Collectors.toList());
	}

}
