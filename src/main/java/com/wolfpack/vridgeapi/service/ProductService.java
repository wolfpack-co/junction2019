package com.wolfpack.vridgeapi.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.wolfpack.vridgeapi.model.Product;
import com.wolfpack.vridgeapi.model.ProductStatus;
import com.wolfpack.vridgeapi.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostConstruct
	private void init() {
		productRepository.save(new Product("domat", 0, 0, "milko", "kolю", new Date(), ProductStatus.AVAILABLE));
	}

	public List<Product> getAllAvailable() {
		return productRepository.findAllByStatus(ProductStatus.AVAILABLE);
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void bookProduct(Product product) {
		Product p = productRepository.getOne(product.getId());
		if (product.getBookedQuantity() > p.getQuantity()) {
			throw new IllegalArgumentException("The specified amount is not available");
		}
		else {
			p.setStatus(ProductStatus.BOOKING_PENDING);
			p.setConsumer(product.getConsumer());
			p.setBookedQuantity(product.getBookedQuantity());
			productRepository.save(product);
		}
	}

	public void confirmBooking(Product product) {
		Product p = productRepository.getOne(product.getId());
		if (ProductStatus.BOOKING_PENDING.equals(p.getStatus())) {
			p.setStatus(ProductStatus.BOOKING_CONFIRMED);
			productRepository.save(p);
		}
	}

	public void completeBooking(Product product) {
		Product p = productRepository.getOne(product.getId());
		if (ProductStatus.BOOKING_CONFIRMED.equals(p.getStatus())) {
			int newQuantity = p.getQuantity() - p.getBookedQuantity();
			ProductStatus newStatus = newQuantity > 0 ? ProductStatus.AVAILABLE : ProductStatus.TAKEN;
			p.setStatus(newStatus);
			p.setQuantity(newQuantity);
			p.setBookedQuantity(0);
			productRepository.save(p);
		}
	}
}
