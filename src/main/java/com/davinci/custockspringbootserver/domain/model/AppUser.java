package com.davinci.custockspringbootserver.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   @JsonIgnore
   private String password;
   @ManyToMany(fetch = FetchType.EAGER)
   private Collection<Role> roles = new ArrayList<>();
}
