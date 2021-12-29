package com.davinci.custockspringbootserver.services.interfece;

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

public interface ProductService {
    Product create(CreateProductDto dto) throws Exception;
    Supplier createSupplier(CreateSupplierDto dto) throws Exception;
    Receipt receiveProduct(ReceiveProductDto dto) throws ProductNotFoundException, SupplierNotFoundException, UserNotFoundException;
    Invoice invoiceProduct(InvoiceProductDto dto) throws ProductNotFoundException, InsufficientProductQuantityException, UserNotFoundException;
}
