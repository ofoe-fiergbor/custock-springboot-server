package com.davinci.custockspringbootserver.domain.repositories;

import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAllByUser(AppUser user);
    Optional<Receipt> findByIdAndUser(long id, AppUser user);
}
