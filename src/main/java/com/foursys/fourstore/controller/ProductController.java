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

import com.foursys.fourstore.dto.ProductDTO;
import com.foursys.fourstore.model.Product;
import com.foursys.fourstore.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findBydId(id));
    }

    @GetMapping("/bySku/{sku}")
    public ResponseEntity<ProductDTO> findBySku(@PathVariable String sku) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findBySku(sku));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.update(id, product));
    }

    @PutMapping("/supplyProductStockById/{id}")
    public ResponseEntity<Integer> supplyProductStockById(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.supplyProductStockById(id, quantity));
    }

    @PutMapping("/supplyProductStockBySku/{sku}")
    public ResponseEntity<Integer> supplyProductStockBySku(@PathVariable String sku, @RequestParam Integer quantity) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.supplyProductStockBySku(sku, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}