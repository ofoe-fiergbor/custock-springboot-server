package com.davinci.custockspringbootserver.domain.repositories;

import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByUser(AppUser user);
    Optional<Invoice> findByIdAndUser(long id, AppUser user);
}
