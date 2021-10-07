package com.example.studentmanager.model;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue
    private long id;


    @Column(name="address")
    String address;

    @ManyToOne
    private Student student;

    public Address() {

    }

    public Address(long id, String address, Student student) {
        this.id = id;
        this.address = address;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
