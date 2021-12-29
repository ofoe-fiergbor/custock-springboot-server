package com.davinci.custockspringbootserver.domain.dto.auth;

import lombok.Data;

@Data
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
