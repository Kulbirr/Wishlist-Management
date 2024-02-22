package com.example.Wishlist.Management.Services;

import com.example.Wishlist.Management.Entities.Product;
import com.example.Wishlist.Management.Exceptions.ProductNotFoundException;
import com.example.Wishlist.Management.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public String addProduct(Product product){
        productRepository.save(product);
        return "Product added successfully";
    }

    public String deleteProduct(int productId)throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            throw new ProductNotFoundException("No product found with given productId" + productId);
        }

        Product productToBeDeleted = product.get();
        productRepository.delete(productToBeDeleted);
        return "Product deleted Successfully!";
    }

    public String updateProductPrice(Integer productId,Integer productPrice) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()) {
            throw new ProductNotFoundException("No product found with given productId" + productId);
        }
        Product productPriceToBeUpdate = product.get();
        productPriceToBeUpdate.setPrice(productPrice);
        productRepository.save(productPriceToBeUpdate);
        return "Price for the given productId " + productId + " has been successfully updated to "+ productPrice;
    }

}
