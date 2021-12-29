package com.davinci.custockspringbootserver.services.interfece;

import com.davinci.custockspringbootserver.domain.dto.product.CreateProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.product.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.ReceiveProductDto;
import com.davinci.custockspringbootserver.domain.model.Invoice;
import com.davinci.custockspringbootserver.domain.model.Product;
import com.davinci.custockspringbootserver.domain.model.Receipt;
import com.davinci.custockspringbootserver.domain.model.Supplier;
import com.davinci.custockspringbootserver.exceptions.*;

import java.util.List;

public interface ProductService {
    Product create(CreateProductDto dto) throws Exception;
    List<Supplier> fetchSuppliers() throws UserNotFoundException;
    List<Product> fetchAllProducts() throws UserNotFoundException;
    Supplier createSupplier(CreateSupplierDto dto) throws Exception;
    Receipt fetchReceipt(long id) throws TransactionNotFoundException, UserNotFoundException;
    Invoice fetchInvoice(long id) throws TransactionNotFoundException, UserNotFoundException;
    List<Receipt> fetchAllReceipts() throws UserNotFoundException;
    List<Invoice> fetchAllInvoices() throws UserNotFoundException;
    Product fetchProduct(Long productId) throws UserNotFoundException, ProductNotFoundException;
    Supplier fetchSupplier(Long supplierId) throws SupplierNotFoundException, UserNotFoundException;
    Receipt receiveProduct(ReceiveProductDto dto) throws ProductNotFoundException, SupplierNotFoundException, UserNotFoundException;
    Invoice invoiceProduct(InvoiceProductDto dto) throws ProductNotFoundException, InsufficientProductQuantityException, UserNotFoundException;

}
