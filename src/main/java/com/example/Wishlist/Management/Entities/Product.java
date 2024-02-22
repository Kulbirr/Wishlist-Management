package com.example.Wishlist.Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Product Name is required")
    private String productName;

    private double rating;

    @NotNull(message = "Price is required")
    private Integer price;

    private String description;

    private String category;

    @NotBlank(message = "Brand Name is required")
    private String brandName;

    private String sellerName;

    @ManyToMany
    @JoinColumn()
    private Set<Wishlist> wishListList = new LinkedHashSet<>();

    
}
