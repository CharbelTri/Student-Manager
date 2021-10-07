package com.example.studentmanager;

import com.example.studentmanager.dao.AddressDao;
import com.example.studentmanager.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    @GetMapping("/address")
    public ResponseEntity<List<Address>> allAddress()
    {
        List<Address> addresses = addressDao.findAll();
        return addresses.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT ) :
                new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> address(@PathVariable("id") long id)
    {
        Optional<Address> addressOptional = addressDao.findById(id);
        return addressOptional
                .map(address -> new ResponseEntity<>(address, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/address/{name}")
    public ResponseEntity<List<Address>> RecupAllAddressToStudent(@RequestParam("name") String name)
    {
        List<Address> addresses = addressDao.findAllAddressForOneStudent(name);
        return addresses.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(addresses, HttpStatus.OK);
    }


    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address)
    {
        Optional<Address> addressData = addressDao.findById(id);
        if (addressData.isPresent())
        {
            Address address1 = addressData.get();
            address1.setAddress(address.getAddress());
            return new ResponseEntity<>(addressDao.save(address1), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id)
    {
        try {
            addressDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/address")
    public ResponseEntity<HttpStatus> deleteAlladdress()
    {
        try {
            addressDao.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
