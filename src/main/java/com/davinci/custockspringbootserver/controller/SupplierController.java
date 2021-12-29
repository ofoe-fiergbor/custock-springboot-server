package com.davinci.custockspringbootserver.controller;


import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.exceptions.ProductNotFoundException;
import com.davinci.custockspringbootserver.exceptions.SupplierNotFoundException;
import com.davinci.custockspringbootserver.exceptions.UserNotFoundException;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> createSupplier(@RequestBody CreateSupplierDto dto) {
        try{
            return new ResponseEntity<>(productService.createSupplier(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchSupplier(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(productService.fetchSupplier(id), HttpStatus.OK);
        } catch (UserNotFoundException | SupplierNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> fetchSuppliers() {
        try {
            return new ResponseEntity<>(productService.fetchSuppliers(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
