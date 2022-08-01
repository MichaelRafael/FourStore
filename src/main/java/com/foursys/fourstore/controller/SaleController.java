package com.foursys.fourstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foursys.fourstore.dto.SaleDTO;
import com.foursys.fourstore.model.Sale;
import com.foursys.fourstore.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(saleService.findById(id));
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<SaleDTO>> findByUser(@RequestParam Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleService.findByUser(userId));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@RequestBody Sale sale) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleService.save(sale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        saleService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}