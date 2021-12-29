package com.davinci.custockspringbootserver.domain.dto.auth;

import lombok.Data;

@Data
public class AddRoleToUserDto {
    private String email;
    private String roleName;
}