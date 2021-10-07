package com.example.studentmanager.dao;

import com.example.studentmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    @Query("select u from Student u where u.name = ?1")
    public Optional<Student> findByName(String name);
}
