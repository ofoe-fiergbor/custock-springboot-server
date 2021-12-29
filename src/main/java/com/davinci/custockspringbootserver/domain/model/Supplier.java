package com.davinci.custockspringbootserver.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.EAGER) @JsonIgnore
    private AppUser user;

    public Supplier(String name, String phoneNumber, AppUser user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
