package com.davinci.custockspringbootserver.domain.dto.auth;

import com.davinci.custockspringbootserver.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private Collection<Role> roles = new ArrayList<>();

}
