package com.davinci.custockspringbootserver.domain.dto.product;

import com.davinci.custockspringbootserver.utils.enums.SocialMedia;
import lombok.Data;


@Data
public class InvoiceProductDto {
    private String description;
    private String buyer;
    private SocialMedia socialMedia;
    private double quantity;
    private Long productId;
    private Long userId;
}
