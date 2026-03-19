package com.nerdcoder.productapp.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsInventoryCsvRepresentation {

  @CsvBindByName(column = "productId")
  private String productId;
  @CsvBindByName(column = "productName")
  private String productName;
  @CsvBindByName(column = "productPrice")
  private Double productPrice;
  @CsvBindByName(column = "productManufacturer")
  private String manufacturer;

}
