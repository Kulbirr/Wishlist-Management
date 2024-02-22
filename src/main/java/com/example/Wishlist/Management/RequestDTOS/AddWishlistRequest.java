package com.example.Wishlist.Management.RequestDTOS;

import com.example.Wishlist.Management.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddWishlistRequest {
    private String wishlistName;

    private String description;

    private Set<Product> productList = new LinkedHashSet<>();

}
