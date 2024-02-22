package com.example.Wishlist.Management.Controllers;

import com.example.Wishlist.Management.Entities.Wishlist;

import com.example.Wishlist.Management.Exceptions.ProductNotFoundException;
import com.example.Wishlist.Management.Exceptions.UserNotFoundException;
import com.example.Wishlist.Management.Exceptions.WishlistNotFoundException;
import com.example.Wishlist.Management.RequestDTOS.AddWishlistRequest;
import com.example.Wishlist.Management.Services.WishListService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("wishlist/")
public class WishListController {
    @Autowired
    private WishListService wishlistService;

    @PostMapping("create")
    public ResponseEntity createWishlist(@RequestBody AddWishlistRequest addWishlistRequest) {
        String result = wishlistService.createWishlist(addWishlistRequest);
        return new ResponseEntity(result, HttpStatus.CREATED);

    }

    @PostMapping("add-Product/{wishlistName}/{productId}")
    public ResponseEntity addProductToWishlist(@PathVariable Integer productId, @PathVariable String wishlistName) {
        try {
            String result = wishlistService.addProductToWishlist(wishlistName, productId);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getWishlist/{wishlistName}")
    public ResponseEntity getWishList(@PathVariable String wishlistName){
        try {
            AddWishlistRequest wishlistSet = wishlistService.getWishList(wishlistName);
            return new ResponseEntity(wishlistSet, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAllWishlists")
    public ResponseEntity getAllWishList(){
        try {
            List<AddWishlistRequest> wishlistRequestList = wishlistService.getAllWishList();
            return new ResponseEntity(wishlistRequestList, HttpStatus.OK);
        }catch(UserNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteItem-fromWishlist/{wishlistName}/{productId}")
    public ResponseEntity deleteItemByIdFromSpecificWishlist(@PathVariable String wishlistName, @PathVariable Integer productId) {
        try {
            String result = wishlistService.deleteItemByIdFromSpecificWishlist(wishlistName, productId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteWishlist/{wishlistName}")
    public ResponseEntity deleteWishlist(@PathVariable String wishlistName){
        try {
            String result = wishlistService.deleteWishlist(wishlistName);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (WishlistNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
