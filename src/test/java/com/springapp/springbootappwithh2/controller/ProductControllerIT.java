package com.springapp.springbootappwithh2.controller;

import com.springapp.springbootappwithh2.SpringBootAppWithH2Application;
import com.springapp.springbootappwithh2.entity.Product;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootAppWithH2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnProduct() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/products/10001"), HttpMethod.GET, entity, String.class);

        String expected = """
                        {
                            "id": 10001,
                            "title": "Apple Macbook Pro",
                            "price": 150000,
                            "description": "Apple Macbook Pro for Development",
                            "category": "Laptop"
                        }
                """;

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void shouldCreateProduct() {

        Product product = new Product();
        product.setTitle("Apple iPad");
        product.setPrice(BigDecimal.valueOf(20000.0));
        product.setDescription("iPad for Development");
        product.setCategory("iPad");

        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/products"),
                HttpMethod.POST, httpEntity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/api/products"));
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
