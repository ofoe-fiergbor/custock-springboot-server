package com.davinci.custockspringbootserver.controller;

import com.davinci.custockspringbootserver.domain.dto.product.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.ReceiveProductDto;
import com.davinci.custockspringbootserver.exceptions.*;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final ProductService productService;


    @PostMapping("/receipts/create")
    public ResponseEntity<?> receiveProduct(@RequestBody ReceiveProductDto dto) {
        try {
            return new ResponseEntity<>(productService.receiveProduct(dto), HttpStatus.CREATED);
        } catch (UserNotFoundException | SupplierNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/invoices/create")
    public ResponseEntity<?> invoiceProduct(@RequestBody InvoiceProductDto dto) {
        try {
            return new ResponseEntity<>(productService.invoiceProduct(dto), HttpStatus.CREATED);
        } catch (UserNotFoundException | ProductNotFoundException | InsufficientProductQuantityException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/receipts")
    public ResponseEntity<?> getReceipts(){
        try {
            return new ResponseEntity<>(productService.fetchAllReceipts(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/invoices")
    public ResponseEntity<?> getInvoices(){
        try {
            return new ResponseEntity<>(productService.fetchAllInvoices(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/receipts/{id}")
    public ResponseEntity<?> getReceipts(@PathVariable long id){
        try {
            return new ResponseEntity<>(productService.fetchReceipt(id), HttpStatus.OK);
        } catch (TransactionNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoices(@PathVariable long id){
        try {
            return new ResponseEntity<>(productService.fetchInvoice(id), HttpStatus.OK);
        } catch (UserNotFoundException | TransactionNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
