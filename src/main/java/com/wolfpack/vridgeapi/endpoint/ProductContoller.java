package com.wolfpack.vridgeapi.endpoint;

import java.util.List;

import com.wolfpack.vridgeapi.model.Product;
import com.wolfpack.vridgeapi.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductContoller {

	private final ProductService productService;

	public ProductContoller(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllAvailableProducts() {
		return ResponseEntity.ok(productService.getAllAvailable());
	}
}
