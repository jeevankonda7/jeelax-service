package com.nerdcoder.productapp.controller;

import com.nerdcoder.productapp.entity.ProductDetails;
import com.nerdcoder.productapp.service.FileProcessingService;
import com.nerdcoder.productapp.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/products")
@Tag(name = "Product APIs", description = "add, update, get, delete the product")
public class ProductController {

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);
  private final ProductServiceImpl productServiceImpl;

  private final FileProcessingService fileProcessingService;

  public ProductController(ProductServiceImpl productServiceImpl,
                           FileProcessingService fileProcessingService) {
    this.productServiceImpl = productServiceImpl;
    this.fileProcessingService = fileProcessingService;
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

  @GetMapping("/writeDataToCsv")
  public ResponseEntity<String> writeToCsvFile() {
    final List<ProductDetails> productDetails = List.of(
            new ProductDetails(1, "PROD-001", "Washing Machine", 42000.00, "Samsung"),
            new ProductDetails(2, "PROD-002", "Camera", 69000.00, "Canon"),
            new ProductDetails(3, "PROD-003", "Refrigerator", 55000.00, "LG"),
            new ProductDetails(4, "PROD-004", "Laptop", 75000.00, "Dell"),
            new ProductDetails(5, "PROD-005", "Smartphone", 30000.00, "OnePlus"));
    this.fileProcessingService.writeRecordsToCsv(productDetails);
    return
            ResponseEntity.ok("Data written successfully");

  }
}
