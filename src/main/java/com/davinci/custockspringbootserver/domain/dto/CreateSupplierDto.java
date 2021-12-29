package com.davinci.custockspringbootserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierDto {
    private String name;
    private String phoneNumber;
    private Long userId;
}
