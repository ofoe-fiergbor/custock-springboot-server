package com.davinci.custockspringbootserver.domain.dto.product;

import lombok.Data;


@Data
public class ReceiveProductDto {
    private String description;
    private Double quantity;
    private Long supplierId;
    private Long productId;
    private Long userId;
}
