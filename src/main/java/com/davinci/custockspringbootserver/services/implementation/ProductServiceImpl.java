package com.davinci.custockspringbootserver.services.implementation;

import com.davinci.custockspringbootserver.domain.dto.product.CreateProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.product.InvoiceProductDto;
import com.davinci.custockspringbootserver.domain.dto.product.ReceiveProductDto;
import com.davinci.custockspringbootserver.domain.model.*;
import com.davinci.custockspringbootserver.domain.repositories.*;
import com.davinci.custockspringbootserver.exceptions.*;
import com.davinci.custockspringbootserver.services.interfece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        AppUser user = getUserById(dto.getUserId());
        Supplier supplier = getSupplier(dto.getSupplierId(), user);
        return productRepository.save(new Product(dto.getName(), dto.getUnitOfMeasurement(), supplier, user));
    }

    @Override
    public Product fetchProduct(Long productId) throws UserNotFoundException, ProductNotFoundException {
        return productRepository.findProductByIdAndUser(productId, getUserByContext())
                .orElseThrow(()-> new ProductNotFoundException("Product with id: "+productId+" doesn't exist"));
    }

    @Override
    public List<Product> fetchAllProducts() throws UserNotFoundException {
        return productRepository.findProductsByUser(getUserByContext());
    }


    @Override
    public Supplier createSupplier(CreateSupplierDto dto) throws UserNotFoundException {
        AppUser user = getUserById(dto.getUserId());
        return supplierRepository.save(new Supplier(dto.getName(), dto.getPhoneNumber(), user));
    }

    @Override
    public Supplier fetchSupplier(Long supplierId) throws SupplierNotFoundException, UserNotFoundException {
        return supplierRepository.findSupplierByIdAndUser(supplierId, getUserByContext())
                .orElseThrow(() -> new SupplierNotFoundException("Supplier with id: "+supplierId+" doesn't exist"));
    }

    @Override
    public List<Supplier> fetchSuppliers() throws UserNotFoundException {
        return supplierRepository.findSuppliersByUser(getUserByContext());
    }


    @Override
    public Receipt receiveProduct(ReceiveProductDto dto) throws ProductNotFoundException, SupplierNotFoundException, UserNotFoundException {
        AppUser user = getUserById(dto.getUserId());
        Product product = getProduct(dto.getProductId(), user);
        Supplier supplier = getSupplier(dto.getSupplierId(), user);
        product.setBalance(product.getBalance() + dto.getQuantity());
        productRepository.save(product);
        return receiptRepository.save(new Receipt(dto.getDescription(), dto.getQuantity(), supplier, product, user));
    }

    @Override
    public Receipt fetchReceipt(long id) throws TransactionNotFoundException, UserNotFoundException {
        return receiptRepository.findByIdAndUser(id, getUserByContext())
                .orElseThrow(() -> new TransactionNotFoundException("Receipt with id: "+id+" doesn't exist"));
    }

    @Override
    public List<Receipt> fetchAllReceipts() throws UserNotFoundException {
        return receiptRepository.findAllByUser(getUserByContext());
    }


    @Override
    public Invoice invoiceProduct(InvoiceProductDto dto) throws ProductNotFoundException, InsufficientProductQuantityException, UserNotFoundException {
        AppUser user = getUserById(dto.getUserId());
        Product product = getProduct(dto.getProductId(), user);
        if (product.getBalance() < dto.getQuantity()) {
            throw new InsufficientProductQuantityException("Quantity of the product is insufficient for the invoice");
        }
        product.setBalance(product.getBalance() - dto.getQuantity());
        return invoiceRepository.save(new Invoice(dto.getDescription(), dto.getBuyer(), dto.getSocialMedia(), dto.getQuantity(), product, user));
    }

    @Override
    public Invoice fetchInvoice(long id) throws TransactionNotFoundException, UserNotFoundException {
        return invoiceRepository.findByIdAndUser(id, getUserByContext())
                .orElseThrow(() -> new TransactionNotFoundException("Invoice with id: "+id+" doesn't exist"));
    }

    @Override
    public List<Invoice> fetchAllInvoices() throws UserNotFoundException {
        return invoiceRepository.findAllByUser(getUserByContext());

    }

    private AppUser getUserById(Long userId) throws UserNotFoundException {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User ID is incorrect."));
    }

    private Supplier getSupplier(long supplierId, AppUser user) throws SupplierNotFoundException {
        return supplierRepository.findSupplierByIdAndUser(supplierId, user)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier does not exit."));
    }

    private Product getProduct(long productId, AppUser user) throws ProductNotFoundException {
        return productRepository.findProductByIdAndUser(productId, user)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

    }
    private AppUser getUserByContext() throws UserNotFoundException {
        return appUserRepository.findAppUserByEmail(getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
