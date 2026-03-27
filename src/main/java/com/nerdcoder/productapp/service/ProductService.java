package com.nerdcoder.productapp.service;

import com.nerdcoder.productapp.entity.ProductDetails;

import java.util.Optional;

public interface ProductService {

   ProductDetails saveProduct(ProductDetails product);
   ProductDetails updateProduct(ProductDetails product);
   Optional<ProductDetails> getProductById(Integer id);
   Optional<ProductDetails> getProductByProductId(String productId);
   Integer deleteById(Integer id);
   Integer fetchMaxIdOfProducts();


}
