package com.nerdcoder.productapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "PRODUCT_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  String productId;
  String productName;
  Double price;
  String manufacturer;

}
