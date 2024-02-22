package com.example.Wishlist.Management.Repositories;

import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.Entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<Wishlist, Integer> {


    Optional<Wishlist> findByWishlistName(String wishlistName);
}
