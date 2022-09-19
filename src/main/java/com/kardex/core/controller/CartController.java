package com.kardex.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kardex.core.dto.CartDTO;
import com.kardex.core.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service;
	
	@PutMapping("/out")
	public ResponseEntity<CartDTO> addToCart (@RequestBody CartDTO cart) {		
		return ResponseEntity.ok(service.sellProducts(cart));
	}
	
	//TODO: faltaria la devolucion, pero eso no esta contemplado en la consigna
}
