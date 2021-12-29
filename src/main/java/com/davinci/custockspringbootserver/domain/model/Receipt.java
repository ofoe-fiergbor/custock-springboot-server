package com.davinci.custockspringbootserver.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Date timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser user;

    public Receipt(String description, Double quantity, Supplier supplier, Product product, AppUser user) {
        this.description = description;
        this.quantity = quantity;
        this.supplier = supplier;
        this.product = product;
        this.user = user;
        this.timestamp= new Timestamp(new Date().getTime());
    }
}
