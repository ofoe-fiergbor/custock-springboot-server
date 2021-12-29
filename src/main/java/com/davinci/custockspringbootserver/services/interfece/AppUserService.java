package com.davinci.custockspringbootserver.services.interfece;

import com.davinci.custockspringbootserver.domain.dto.auth.CreateUserDto;
import com.davinci.custockspringbootserver.domain.dto.auth.LoginResponse;
import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Role;

public interface AppUserService {
    AppUser saveUser(CreateUserDto user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    LoginResponse authenticateUser(String email, String password, AppUser appUser);

}
