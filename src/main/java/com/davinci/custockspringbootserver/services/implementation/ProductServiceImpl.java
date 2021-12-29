package com.davinci.custockspringbootserver.services.implementation;

import com.davinci.custockspringbootserver.domain.dto.CreateProductDto;
import com.davinci.custockspringbootserver.domain.dto.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.ReceiveProductDto;
import com.davinci.custockspringbootserver.domain.model.*;
import com.davinci.custockspringbootserver.domain.repositories.*;
import com.davinci.custockspringbootserver.exceptions.InsufficientProductQuantityException;
import com.davinci.custockspringbootserver.exceptions.ProductNotFoundException;
import com.davinci.custockspringbootserver.exceptions.SupplierNotFoundException;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ReceiptRepository receiptRepository;
    private final AppUserRepository appUserRepository;
    private final InvoiceRepository invoiceRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public Product create(CreateProductDto dto) throws Exception {
        AppUser user = getUser(dto.getUserId());
        Supplier supplier = getSupplier(dto.getSupplierId(), user);
        return productRepository.save(new Product(dto.getName(), dto.getUnitOfMeasurement(), supplier, user));
    }



    @Override
    public Supplier createSupplier(CreateSupplierDto dto) {
        AppUser user = getUser(dto.getUserId());
        return supplierRepository.save(new Supplier(dto.getName(), dto.getPhoneNumber(), user));
    }

    @Override
    public Receipt receiveProduct(ReceiveProductDto dto) throws ProductNotFoundException, SupplierNotFoundException {
        AppUser user = getUser(dto.getUserId());
        Product product = getProduct(dto.getProductId(), user);
        Supplier supplier = getSupplier(dto.getSupplierId(), user);
        product.setBalance(product.getBalance() + dto.getQuantity());
        productRepository.save(product);
        return receiptRepository.save(new Receipt(dto.getDescription(), dto.getQuantity(), supplier, product, user));
    }

    @Override
    public Invoice invoiceProduct(InvoiceProductDto dto) throws ProductNotFoundException, InsufficientProductQuantityException {
        AppUser user = getUser(dto.getUserId());
        Product product = getProduct(dto.getProductId(), user);
        if (product.getBalance() < dto.getQuantity()) {
            throw new InsufficientProductQuantityException("Quantity of the product is insufficient for the invoice");
        }
        product.setBalance(product.getBalance() - dto.getQuantity());
        return invoiceRepository.save(new Invoice(dto.getDescription(), dto.getBuyer(), dto.getSocialMedia(), dto.getQuantity(), product, user));
    }

    private AppUser getUser(Long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User ID is incorrect."));
    }

    private Supplier getSupplier(long supplierId, AppUser user) throws SupplierNotFoundException {
        return supplierRepository.findSupplierByIdAndUser(supplierId, user)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier does not exit."));
    }

    private Product getProduct(long productId, AppUser user) throws ProductNotFoundException {
        return productRepository.findProductByIdAndUser(productId, user)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

    }
}
