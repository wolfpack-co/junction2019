package com.wolfpack.vridgeapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wolfpack.vridgeapi.model.Product;
import com.wolfpack.vridgeapi.model.ProductStatus;
import com.wolfpack.vridgeapi.model.User;
import com.wolfpack.vridgeapi.repository.ProductRepository;

import com.wolfpack.vridgeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	private static final String DEFAULT_EXPIRATION_DATE_STRING = "2019-11-20";

	@Autowired
	public ProductService(ProductRepository productRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	public List<Product> getAllAvailable() {
		return productRepository.findAllByStatus(ProductStatus.AVAILABLE);
	}

	public List<Product> getAllProductsByUser(int creatorId) {

		List<Product> products = productRepository.findAllByCreatorId(creatorId);
		if (!products.isEmpty()) {
			return products;
		}
		return new ArrayList<>();
	}

	public List<Product> getProductsForAllExcept(int creatorId) {
		List<Product> products = productRepository.findAllByCreatorIdNot(creatorId);
		if (!products.isEmpty()) {
			return products;
		}
		return new ArrayList<>();
	}

	public void changeSharedProductState(int productId, boolean shared) {
		Product p = productRepository.getOne(productId);
		p.setShared(shared);

		productRepository.save(p);
	}

	public Product addProduct(Product product) {
		product.setStatus(ProductStatus.AVAILABLE);
		User creator = userRepository.findById(product.getCreator().getId());
		product.setCreator(creator);
		if (product.getExpirationDate() == null) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DEFAULT_EXPIRATION_DATE_STRING);
				product.setExpirationDate(date);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return productRepository.save(product);
	}

	public void bookProduct(int id, Product product) {
		Product p = productRepository.findById(id).get();

//		if (product.getBookedQuantity() > p.getQuantity()) {
//			throw new IllegalArgumentException("The specified amount is not available");
//		}
//		else {
			p.setStatus(ProductStatus.BOOKING_PENDING);
			User consumer = userRepository.findById(product.getConsumer().getId());
			p.setConsumer(consumer);
//			p.setShared(false);
			p.setBookedQuantity(p.getQuantity());
			productRepository.save(p);
//		}
	}

	public void confirmBooking(int id) {
		Product p = productRepository.findById(id).get();
		if (ProductStatus.BOOKING_PENDING.equals(p.getStatus())) {
			p.setStatus(ProductStatus.BOOKING_CONFIRMED);
			productRepository.save(p);
		} else {
			throw new IllegalArgumentException("Cannot confirm a booking that is not Pending");
		}
	}

	public void completeBooking(int id) {
		Product p = productRepository.findById(id).get();
		if (ProductStatus.BOOKING_CONFIRMED.equals(p.getStatus())) {
			int newQuantity = p.getQuantity() - p.getBookedQuantity();
			ProductStatus newStatus = newQuantity > 0 ? ProductStatus.AVAILABLE : ProductStatus.TAKEN;
			if (newStatus.equals(ProductStatus.TAKEN)) {
				// product which is with status TAKEN and NOT SHARED means a realized booking
				Product newProduct = new Product(p.getName(), p.getBookedQuantity(), 0, p.getCreator(), null, p.getExpirationDate(),
						false, newStatus, p.getType());
				p = newProduct;
			}

			// update quantity and status of existing product
			p.setStatus(newStatus);
			p.setQuantity(newQuantity);
			productRepository.save(p);
		} else {
			throw new IllegalArgumentException("Cannot complete booking that has not been confirmed yet");
		}
	}
}
