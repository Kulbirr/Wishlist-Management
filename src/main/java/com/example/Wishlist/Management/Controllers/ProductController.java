package com.example.Wishlist.Management.Controllers;

import com.example.Wishlist.Management.Entities.Product;
import com.example.Wishlist.Management.Exceptions.ProductNotFoundException;
import com.example.Wishlist.Management.Repositories.ProductRepository;
import com.example.Wishlist.Management.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("add")
    public ResponseEntity addProduct(@RequestBody Product product){
        String result = productService.addProduct(product);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int productId) {
        try {
            String result = productService.deleteProduct(productId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{productId}/{price}")
    public ResponseEntity updateProductPrice(@PathVariable Integer productId, @PathVariable Integer price){
        try{
            String result = productService.updateProductPrice(productId, price);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
