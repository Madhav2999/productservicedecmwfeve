package com.scaler.productservicedecmwfeve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;
}
