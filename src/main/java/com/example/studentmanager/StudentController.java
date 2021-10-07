package com.example.studentmanager;

import com.example.studentmanager.dao.AddressDao;
import com.example.studentmanager.model.Address;
import com.example.studentmanager.model.Student;
import com.example.studentmanager.dao.StudentDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@Api("/student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AddressDao addressDao;


    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public ResponseEntity<List<Student>> students()
    {
        List<Student> students = studentDao.findAll();
        return students.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> student(@PathVariable("id") long id)
    {
        Optional<Student> studentOptional = studentDao.findById(id);
        return studentOptional
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Student> FindOneStudent(@RequestParam(value="name") String name)
    {
        Optional<Student> student = studentDao.findByName(name);
        return student
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Void> addStudent(@RequestBody Student student)
    {
        Student student1 = studentDao.save(new Student(student.getId(), student.getName()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/address/{name}")
    public ResponseEntity<HttpStatus> addAddress(@PathVariable("name") String name, @RequestBody Address address)
    {
        Optional<Student> studentOptional = studentDao.findByName(name);
        if (studentOptional.isPresent())
        {
            Student student = studentOptional.get();
            Address address1 = addressDao.save(new Address(address.getId(), address.getAddress(), student));
            student.setAddresses(address1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student)
    {
        Optional<Student> studentData = studentDao.findById(id);
        if (studentData.isPresent())
        {
            Student student1 = studentData.get();
            student1.setName(student.getName());
            return new ResponseEntity<>(studentDao.save(student1), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id)
    {
        try {
            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllStudent()
    {
        try {
            studentDao.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
