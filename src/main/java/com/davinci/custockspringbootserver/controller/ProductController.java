package com.davinci.custockspringbootserver.controller;

import com.davinci.custockspringbootserver.domain.dto.product.CreateProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.product.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.ReceiveProductDto;
import com.davinci.custockspringbootserver.domain.model.Invoice;
import com.davinci.custockspringbootserver.domain.model.Product;
import com.davinci.custockspringbootserver.domain.model.Receipt;
import com.davinci.custockspringbootserver.domain.model.Supplier;
import com.davinci.custockspringbootserver.exceptions.InsufficientProductQuantityException;
import com.davinci.custockspringbootserver.exceptions.ProductNotFoundException;
import com.davinci.custockspringbootserver.exceptions.SupplierNotFoundException;
import com.davinci.custockspringbootserver.exceptions.UserNotFoundException;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto dto) {
        try {
            Product newProduct = productService.create(dto);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> fetchProducts() {
        try {
            return new ResponseEntity<>(productService.fetchAllProducts(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchProduct(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(productService.fetchProduct(id), HttpStatus.OK);
        } catch (UserNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
