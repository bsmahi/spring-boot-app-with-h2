package com.springapp.springbootappwithh2.controller;

import com.springapp.springbootappwithh2.entity.Product;
import com.springapp.springbootappwithh2.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;


    @Test
    public void shouldReturnProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setId(10003L);
        mockProduct.setTitle("Apple Macbook Pro");
        mockProduct.setPrice(BigDecimal.valueOf(150000));
        mockProduct.setDescription("Apple Macbook Pro for Development");
        mockProduct.setCategory("Tablet");

        when(service.findProductById(10003)).thenReturn(Optional.of(mockProduct));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/10003")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = """
                        {
                            "id": 10003,
                            "title": "Apple Macbook Pro",
                            "price": 150000,
                            "description": "Apple Macbook Pro for Development",
                            "category": "Tablet"
                        }
                """;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setId(10003L);
        mockProduct.setTitle("Apple Macbook Pro");
        mockProduct.setPrice(BigDecimal.valueOf(150000));
        mockProduct.setDescription("Apple Macbook Pro for Development");
        mockProduct.setCategory("Tablet");

        String exampleProductJson = """
                        {
                            "id": 10003,
                            "title": "Apple Macbook Pro",
                            "price": 150000,
                            "description": "Apple Macbook Pro for Development",
                            "category": "Tablet"
                        }
                """;

        // service.createProduct to respond back with mockProduct
        when(service.createProduct(Mockito.any(Product.class))).thenReturn(Optional.of(mockProduct));

        // Send product as body to /api/products
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/products")
                .accept(MediaType.APPLICATION_JSON).content(exampleProductJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/api/products/10003", response.getHeader(HttpHeaders.LOCATION));

    }
}
