package com.davinci.custockspringbootserver.domain.repositories;

import com.davinci.custockspringbootserver.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
