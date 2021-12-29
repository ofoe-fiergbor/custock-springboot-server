package com.davinci.custockspringbootserver.domain.repositories;

import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findProductsByUser(AppUser user);
    Optional<Product> findProductByIdAndUser(long id, AppUser user);
}
