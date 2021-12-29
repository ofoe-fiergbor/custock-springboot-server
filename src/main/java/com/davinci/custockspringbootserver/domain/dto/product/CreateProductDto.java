package com.davinci.custockspringbootserver.domain.dto.product;

import lombok.Data;

@Data
public class CreateProductDto {
    private String name;
    private String unitOfMeasurement;
    private Long supplierId;
    private Long userId;
}
