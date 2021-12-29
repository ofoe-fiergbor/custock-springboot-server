package com.davinci.custockspringbootserver.controller;

import com.davinci.custockspringbootserver.domain.dto.product.CreateProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.product.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.ReceiveProductDto;
import com.davinci.custockspringbootserver.domain.model.Invoice;
import com.davinci.custockspringbootserver.domain.model.Product;
import com.davinci.custockspringbootserver.domain.model.Receipt;
import com.davinci.custockspringbootserver.domain.model.Supplier;
import com.davinci.custockspringbootserver.services.implementation.ProductServiceImpl;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;


    @PostMapping("/products/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto dto) {
        try {
            Product newProduct = productService.create(dto);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/suppliers/create")
    public ResponseEntity<?> createSupplier(@RequestBody CreateSupplierDto dto) {
        try{
            Supplier newSupplier = productService.createSupplier(dto);
            return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products/receive")
    public ResponseEntity<?> receiveProduct(@RequestBody ReceiveProductDto dto) {
        try {
            Receipt newReceipt = productService.receiveProduct(dto);
            return new ResponseEntity<>(newReceipt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products/invoice")
    public ResponseEntity<?> invoiceProduct(@RequestBody InvoiceProductDto dto) {
        try {
            Invoice newInvoice = productService.invoiceProduct(dto);
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
