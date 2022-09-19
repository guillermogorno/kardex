package com.kardex.core.dto;

import java.io.Serializable;

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
public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer productId;
	
	private String productName;
	
	private Integer stock;
	
	private Integer amount;
	
	public boolean isStockAvailable () {
		return stock >= amount;
	}
}
