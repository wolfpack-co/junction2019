package com.wolfpack.vridgeapi.endpoint;

import java.util.List;

import com.wolfpack.vridgeapi.model.Product;
import com.wolfpack.vridgeapi.service.ProductService;
import org.apache.catalina.connector.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllAvailableProducts() {
		return ResponseEntity.ok(productService.getAllAvailable());
	}

	@GetMapping("/creator/{creatorId}")
	public ResponseEntity<List<Product>> getAllProductsPerUser(@PathVariable int creatorId) {
		try {
			return ResponseEntity.ok(productService.getAllProductsByUser(creatorId));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}


	@GetMapping("/creator/{creatorId}/others")
	public ResponseEntity<List<Product>> getProductsForUsersExcept(@PathVariable int creatorId) {
		try {
			return ResponseEntity.ok(productService.getProductsForAllExcept(creatorId));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping
	public ResponseEntity addProductToFridge(@RequestBody Product product) {
		try {
			productService.addProduct(product);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{productId}/{shared}") //changeSharedProductState
	public ResponseEntity changeSharedProductState(@PathVariable("productId") int id,
			@PathVariable("shared") boolean shared, @RequestBody Product product) {
		try {
			productService.changeSharedProductState(id, shared);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{productId}/book")
	public ResponseEntity bookProduct(@PathVariable("productId") int id, @RequestBody Product product) {
		try {
			// TODO set creator on product
			productService.bookProduct(id);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{productId}/confirm")
	public ResponseEntity confirmProductBooking(@PathVariable("productId") int id, @RequestBody Product product) {
		try {
			// TODO set creator on product
			productService.confirmBooking(id);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{productId}/complete")
	public ResponseEntity completeProductBooking(@PathVariable("productId") int id, @RequestBody Product product) {
		try {
			// TODO set creator on product
			productService.completeBooking(id);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
