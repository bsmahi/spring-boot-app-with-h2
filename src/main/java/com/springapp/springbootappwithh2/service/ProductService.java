package com.springapp.springbootappwithh2.service;

import com.springapp.springbootappwithh2.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Optional<Product> findProductById(long productId);

    void deleteProductById(long id);

    Optional<Product> createProduct(Product product);

    Optional<Product> updateProduct(Product product);
}
