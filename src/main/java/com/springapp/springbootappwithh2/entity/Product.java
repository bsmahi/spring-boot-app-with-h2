package com.springapp.springbootappwithh2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "product_details")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_price")
    private BigDecimal price;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_category")
    private String category;

}
