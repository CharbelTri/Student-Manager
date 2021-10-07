package com.example.studentmanager.dao;

import com.example.studentmanager.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a INNER JOIN Student s ON a.student.id = s.id WHERE s.name = ?1")
    public List<Address> findAllAddressForOneStudent(String name);
}
