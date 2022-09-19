package com.kardex.core.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer categoryId;
	
	private String categoryName;
	
	private List<ProductDTO> products;
	
}
