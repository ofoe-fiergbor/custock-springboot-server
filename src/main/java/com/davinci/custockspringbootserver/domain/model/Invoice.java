package com.davinci.custockspringbootserver.domain.model;

import com.davinci.custockspringbootserver.utils.enums.SocialMedia;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String buyer;
    private SocialMedia socialMedia;
    private double quantity;
    @ManyToOne
    private Product product;
    private Date timestamp;
    @ManyToOne
    private AppUser user;

    public Invoice(String description, String buyer, SocialMedia socialMedia, double quantity, Product product, AppUser user) {
        this.description = description;
        this.buyer = buyer;
        this.socialMedia = socialMedia;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
