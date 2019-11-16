package com.wolfpack.vridgeapi.model;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
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

    public int getId() { return id; }

    public int getFloor() { return floor; }

    public int getApartment() { return apartment; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setFloor(int floor) { this.floor = floor; }
}
