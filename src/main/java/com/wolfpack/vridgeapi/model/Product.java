package com.wolfpack.vridgeapi.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;

@Entity
// Dynamic update might have a performance overhead because it does not use the cached query.
// Use only if we need to update one of many columns
@DynamicUpdate
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;

	@Column
	private int quantity;

	@Column
	private int bookedQuantity;

	@ManyToOne
	private User creator;

	@Column
	private String consumer;

	@Column
	private Date expirationDate;

//	@Column
//	private boolean isAvailable;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	// FIXME add booking object and booking id... some day

	public Product(){

	}

	public Product(String name, int quantity, int bookedQuantity, User creator,
			String consumer, Date expirationDate, ProductStatus status) {
		this.name = name;
		this.quantity = quantity;
		this.bookedQuantity = bookedQuantity;
		this.creator = creator;
		this.consumer = consumer;
		this.expirationDate = expirationDate;
//		this.isAvailable = isAvailable;
		this.status = status;
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

	public void setCreator(User creator) { this.creator = creator; }

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}
}
