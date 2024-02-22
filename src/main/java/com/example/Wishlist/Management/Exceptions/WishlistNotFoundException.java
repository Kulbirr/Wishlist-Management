package com.example.Wishlist.Management.Exceptions;

public class WishlistNotFoundException extends RuntimeException{
    public WishlistNotFoundException(String message) {
        super(message);
    }
}
