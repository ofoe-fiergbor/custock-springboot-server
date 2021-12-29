package com.davinci.custockspringbootserver.domain.repositories;

import com.davinci.custockspringbootserver.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);
    Boolean existsAppUserByEmail(String email);
}
