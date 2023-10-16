package com.springapp.springbootappwithh2.service;

import com.springapp.springbootappwithh2.entity.Product;
import com.springapp.springbootappwithh2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAllProducts() {
        LOGGER.info("Inside ProductServiceImpl getProducts...");
        return repository.findAll();
    }

    @Override
    public Optional<Product> findProductById(long productId) {
        LOGGER.info("Inside ProductServiceImpl getProductsById...{}", productId);
        return repository.findById(productId);
    }

    @Override
    public void deleteProductById(long id) {
        LOGGER.info("Inside ProductServiceImpl deleteProductById...{}", id);
        repository.deleteById(id);
    }

    @Override
    public Optional<Product> createProduct(Product product) {
        LOGGER.info("Create New Product {}", product);
        return Optional.of(repository.save(product));
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        LOGGER.info("Update Product {}", product);
        return Optional.of(repository.save(product));
    }
}