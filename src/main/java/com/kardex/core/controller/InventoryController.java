package com.kardex.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kardex.core.dto.InventoryDTO;
import com.kardex.core.exception.CategoryException;
import com.kardex.core.service.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;
		
	@GetMapping("/all")
	public ResponseEntity<List<InventoryDTO>> getAllInventory () {
		return ResponseEntity.ok(service.getAllInventory());
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<InventoryDTO> getInventoryByCategory (@PathVariable Integer categoryId) throws CategoryException {
		return ResponseEntity.ok(service.getInventoryByCategory(categoryId));
	}
	
	@PutMapping("/in")
	public ResponseEntity<InventoryDTO> increaseProductStock (@RequestBody InventoryDTO inventoryDTO) {
		return ResponseEntity.ok(service.increaseProductStock(inventoryDTO));
	}
}
