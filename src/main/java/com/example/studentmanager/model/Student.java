package com.example.studentmanager.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses.add(addresses);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public Student()
    {

    }
    public Student(long id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
