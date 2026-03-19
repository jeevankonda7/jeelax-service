package com.nerdcoder.productapp.service;

import com.nerdcoder.productapp.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private FileProcessingService fileProcessingService;
  @InjectMocks
  private ProductServiceImpl productServiceImpl;

  @Nested
  @DisplayName("Add Products tests")
  class addProductsTest {

    @Test
    public void testAddProduct() {
       System.out.println("product added");
    }

  }

}