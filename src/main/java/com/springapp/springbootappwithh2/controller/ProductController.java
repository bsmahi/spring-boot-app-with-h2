package com.springapp.springbootappwithh2.controller;

import com.springapp.springbootappwithh2.entity.Product;
import com.springapp.springbootappwithh2.exception.ProductNotFoundException;
import com.springapp.springbootappwithh2.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Find All Product Details")
    public ResponseEntity<List<Product>> getAllProducts() {
        LOGGER.info("Get all the Products...");
        return new ResponseEntity<>(service.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Product by Id")
    public ResponseEntity<Product> getAllProductById(@PathVariable long id) {
        LOGGER.info("Get the product by id...");
        Optional<Product> product = service.findProductById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("id-" + id);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product by Id")
    public void deleteProduct(@PathVariable long id) {
        LOGGER.info("Delete the product by id...");
        service.deleteProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create new Product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Optional<Product> savedProduct = service.createProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.get().getId())
                .toUri();

        return ResponseEntity.created(location)
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product,
                                                @PathVariable long id) {

        Optional<Product> productOptional = service.findProductById(id);

        if (productOptional.isEmpty())
            return ResponseEntity.notFound().build();

        product.setId(id);

        service.updateProduct(product);

        return ResponseEntity.noContent()
                .build();
    }

}
