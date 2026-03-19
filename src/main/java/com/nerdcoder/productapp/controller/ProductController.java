package com.nerdcoder.productapp.controller;

import com.nerdcoder.productapp.entity.ProductDetails;
import com.nerdcoder.productapp.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/products")
@Tag(name = "Product APIs", description = "add, update, get, delete the product")
public class ProductController {

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);
  private final ProductServiceImpl productServiceImpl;

  public ProductController(ProductServiceImpl productServiceImpl) {
    this.productServiceImpl = productServiceImpl;
  }

  @PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ProductDetails saveProductDetails(@RequestBody ProductDetails productDetails) {
    log.info("product save request received");
    return productServiceImpl.saveProduct(productDetails);
  }

  @PostMapping(value = "/uploadProducts", consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> addMultipleProducts(
          @RequestPart("file") MultipartFile productDetailsFile
          ) throws Exception {
    log.info("product inventory file request received");
    return ResponseEntity.ok(
            productServiceImpl
                    .addMultipleProducts(productDetailsFile));

  }
}
