package com.wolfpack.vridgeapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "full_name")
	private String fullName;

	@Column
	private int floor;

	@Column
	private int apartment;

	public User() {

	}

	public User(String fullName, int floor, int apartment) {
		this.fullName = fullName;
		this.floor = floor;
		this.apartment = apartment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getApartment() {
		return apartment;
	}

	public void setApartment(int apartment) {
		this.apartment = apartment;
	}
}
