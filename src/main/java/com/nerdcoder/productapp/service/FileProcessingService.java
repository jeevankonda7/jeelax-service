package com.nerdcoder.productapp.service;

import com.nerdcoder.productapp.entity.ProductDetails;
import com.nerdcoder.productapp.model.ProductsInventoryCsvRepresentation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileProcessingService {

  public Set<ProductDetails> parseCsv(MultipartFile file) throws Exception  {
    try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      HeaderColumnNameMappingStrategy<ProductsInventoryCsvRepresentation> productFileHeadersStrategy =
      new HeaderColumnNameMappingStrategy<>();
      productFileHeadersStrategy.setType(ProductsInventoryCsvRepresentation.class);
      CsvToBean<ProductsInventoryCsvRepresentation> csvToBean =
              new CsvToBeanBuilder<ProductsInventoryCsvRepresentation>(reader)
                      .withMappingStrategy(productFileHeadersStrategy)
                      .withIgnoreEmptyLine(true)
                      .withIgnoreLeadingWhiteSpace(true)
                      .build();

      return csvToBean.parse()
              .stream()
              .map(product -> ProductDetails.builder()
                      .productId(product.getProductId())
                      .productName(product.getProductName())
                      .price(product.getProductPrice())
                      .manufacturer(product.getManufacturer())
                      .build())
              .collect(Collectors.toSet());

    }
  }


}
