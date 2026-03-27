package com.nerdcoder.productapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "APPLICATION_USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  String username;
  String email;
  String password;
  String mobileNumber;
  String gender;
}
