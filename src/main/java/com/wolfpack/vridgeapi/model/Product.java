package com.wolfpack.vridgeapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity
// Dynamic update might have a performance overhead because it does not use the cached query.
// Use only if we need to update one of many columns
@DynamicUpdate
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name = "Default product name";

	@Column
	private int quantity = 0;

	@Column
	private int bookedQuantity = 0;

	@ManyToOne
	private User creator;

	@ManyToOne
	private User consumer;

	@Column
	private Date expirationDate = new Date();

	@Column(name = "isShared", columnDefinition = "boolean default false")
	private boolean isShared;

	@Enumerated(EnumType.STRING)
	private ProductStatus status = ProductStatus.AVAILABLE;


	@Enumerated(EnumType.STRING)
	private ProductType type = ProductType.PRODUCT;


	// FIXME add booking object and booking id... some day

	public Product() {

	}

	public Product(String name, int quantity, int bookedQuantity, User creator, User consumer,
			Date expirationDate, boolean isShared, ProductStatus status, ProductType type) {
		this.name = name;
		this.quantity = quantity;
		this.bookedQuantity = bookedQuantity;
		this.creator = creator;
		this.consumer = consumer;
		this.expirationDate = expirationDate;
		this.isShared = isShared;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBookedQuantity() {
		return bookedQuantity;
	}

	public void setBookedQuantity(int bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getConsumer() {
		return consumer;
	}

	public void setConsumer(User consumer) {
		this.consumer = consumer;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean shared) {
		isShared = shared;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}
}
