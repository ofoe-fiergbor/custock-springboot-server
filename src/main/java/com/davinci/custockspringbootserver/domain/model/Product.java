package com.davinci.custockspringbootserver.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double quantity;
    private String unitOfMeasurement;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne @JsonIgnore
    private AppUser user;

}
