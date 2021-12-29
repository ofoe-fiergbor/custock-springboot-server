package com.davinci.custockspringbootserver.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double balance;
    private String unitOfMeasurement;
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.EAGER) @JsonIgnore
    private AppUser user;

    public Product(String name, String unitOfMeasurement, Supplier supplier, AppUser user) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.supplier = supplier;
        this.user = user;
        this.balance = 0.00;
    }
}
