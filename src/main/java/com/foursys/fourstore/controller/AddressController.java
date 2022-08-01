package com.foursys.fourstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foursys.fourstore.dto.AddressDTO;
import com.foursys.fourstore.model.Address;
import com.foursys.fourstore.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    
	@Autowired
    private AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<AddressDTO>> findAllAddress() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
    }

    @GetMapping("/byUser/userId")
    public ResponseEntity<List<AddressDTO>> findByUser(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressService.findByUser(userId));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody Address address, @RequestParam Long userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.saveAddress(userId, address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody Address address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
