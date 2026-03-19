package com.nerdcoder.productapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String productId;
  private String productName;
  private Double price;
  private String manufacturer;

//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//  public String getProductId() {
//    return productId;
//  }
//
//  public void setProductId(String productId) {
//    this.productId = productId;
//  }
//
//  public String getProductName() {
//    return productName;
//  }
//
//  public void setProductName(String productName) {
//    this.productName = productName;
//  }
//
//  public Double getPrice() {
//    return price;
//  }
//
//  public void setPrice(Double price) {
//    this.price = price;
//  }
//
//  public String getManufacturer() {
//    return manufacturer;
//  }
//
//  public void setManufacturer(String manufacturer) {
//    this.manufacturer = manufacturer;
//  }
}
