package com.davinci.custockspringbootserver.services.interfece;

import com.davinci.custockspringbootserver.domain.dto.auth.CreateUserDto;
import com.davinci.custockspringbootserver.domain.dto.auth.LoginResponse;
import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Role;
import com.davinci.custockspringbootserver.exceptions.UserNotFoundException;

public interface AppUserService {
    String logout();
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    AppUser saveUser(CreateUserDto user) throws UserNotFoundException;
    LoginResponse authenticateUser(String email, String password, AppUser appUser);

}
