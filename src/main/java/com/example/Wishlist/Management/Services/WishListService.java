package com.example.Wishlist.Management.Services;

import com.example.Wishlist.Management.Entities.Product;
import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.Entities.Wishlist;
import com.example.Wishlist.Management.Exceptions.ProductNotFoundException;
import com.example.Wishlist.Management.Exceptions.UserNotFoundException;
import com.example.Wishlist.Management.Exceptions.WishlistNotFoundException;
import com.example.Wishlist.Management.Repositories.ProductRepository;
import com.example.Wishlist.Management.Repositories.UserRepository;
import com.example.Wishlist.Management.Repositories.WishListRepository;
import com.example.Wishlist.Management.RequestDTOS.AddWishlistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public String createWishlist(AddWishlistRequest addWishlistRequest) {
//        Currently logged-in user
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Fetching the user from the database
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            return "User not found!";
        }
        User user1 = user.get();

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user1);
        wishlist.setDescription(addWishlistRequest.getDescription());
        wishlist.setWishlistName(addWishlistRequest.getWishlistName());
        wishlist.setProductList(addWishlistRequest.getProductList());
        wishlistRepository.save(wishlist);
        return "Wishlist created successfully!";
    }

    public String addProductToWishlist(String wishlistName, Integer productId) throws ProductNotFoundException, UserNotFoundException {
//        Currently logged-in user
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Fetching the user which is currently logged in from its username;
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found with given username" + userName);
        }
        User user1 = user.get();

//        Fetching the wishlist from the user
        List<Wishlist> wishlist = user1.getWishlistList();

//        Fetching the product from the database
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("No product found with given productId" + productId);
        }
        Product productToBeAddedInWishlist = product.get();

// If wishlist exists, add the product to the wishlist
        for (Wishlist w : wishlist) {
            if (w.getWishlistName().equals(wishlistName)) {
                w.getProductList().add(productToBeAddedInWishlist);
                wishlistRepository.save(w);
                return "Product added to wishlist successfully!";
            }
        }
//        If wishlist does not exist, create a new wishlist and add the product to the wishlist
        createWishlist(new AddWishlistRequest(wishlistName, "New Wishlist", Set.of(productToBeAddedInWishlist)));
        return "New Wishlist created and product added successfully!";
    }


    public AddWishlistRequest getWishList(String wishlistName)throws UserNotFoundException {
//        Currently logged-in User
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Fetching the user from the database
        Optional<User> user1 = userRepository.findByUserName(userName);
        if (user1.isEmpty()) {
            throw new UserNotFoundException("No user found with given username" + userName);
        }
        User user = user1.get();

//        Fetching the wishlist from the user
        List<Wishlist> wishlist = user.getWishlistList();
//         If wishlist is empty, return null
        if(wishlist.isEmpty()){
            return null;
        }
//        return addWishlistRequest to avoid security issues(Wishlist contains user object which contains password and other sensitive information));
        AddWishlistRequest wishlistRequest = new AddWishlistRequest();

        for(Wishlist w : wishlist){
            if(w.getWishlistName().equals(wishlistName)){
                wishlistRequest.setWishlistName(wishlistName);
                wishlistRequest.setDescription(w.getDescription());
                wishlistRequest.setProductList(w.getProductList());
                return wishlistRequest;
            }
        }
        return null;
    }

    public List<AddWishlistRequest> getAllWishList() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> user1 = userRepository.findByUserName(userName);
        if (user1.isEmpty()){
            throw new UserNotFoundException("No user found with given userName "+ userName);
        }
        User user = user1.get();
        List<AddWishlistRequest> addWishlistRequests = new ArrayList<>();


        List<Wishlist> wishlistList = user.getWishlistList();

//        it was failing because i was not creating new instance of AddWishListRequest in each iteration adn was populating the same object
//        for(Wishlist w : wishlistList){
//        AddWishlistRequest addWishlistRequest = new AddWishlistRequest();
//            addWishlistRequest.setWishlistName(w.getWishlistName());
//            addWishlistRequest.setDescription(w.getDescription());
//            addWishlistRequest.setProductList(w.getProductList());
//            addWishlistRequests.add(addWishlistRequest);
//        }
        for(int i = 0; i < wishlistList.size(); i++){
            AddWishlistRequest addWishlistRequest = new AddWishlistRequest();
            addWishlistRequest.setWishlistName(wishlistList.get(i).getWishlistName());
            addWishlistRequest.setDescription((wishlistList.get(i).getDescription()));
            addWishlistRequest.setProductList(wishlistList.get(i).getProductList());
            addWishlistRequests.add(addWishlistRequest);
        }
        return addWishlistRequests;
    }

    public String deleteItemByIdFromSpecificWishlist(String wishlistName, Integer productId)throws ProductNotFoundException{
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> user1 = userRepository.findByUserName(userName);
        User user = user1.get();

        Optional<Product> product1 = productRepository.findById(productId);
        if(product1.isEmpty()){
            throw new ProductNotFoundException("No product found with given productId "+ productId);
        }
        Product product = product1.get();

        List<Wishlist> wishlistList = user.getWishlistList();
        for(Wishlist w : wishlistList){
            if(w.getWishlistName().equals(wishlistName)){
                w.getProductList().remove(product);
                userRepository.save(user);
                return "Product removed Successfully!";
            }
        }
        return "No wishlist found with given wishlistName "+wishlistName;
    }

    public String deleteWishlist(String wishlistName) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> user1 = userRepository.findByUserName(userName);
        User user = user1.get();

        List<Wishlist> wishlistList = user.getWishlistList();
        for(Wishlist w : wishlistList){
            if(w.getWishlistName().equals(wishlistName)) {
//                had to remove from users list and wishlist repo because of the cascade type;
                user.getWishlistList().remove(w);
                wishlistRepository.delete(w);
                userRepository.save(user);
                return "Wishlist deleted Successfully!";
            }
        }
        throw new WishlistNotFoundException("No wishlist found with given wishlistName "+ "'" + wishlistName+ "'");

//        return "No wishlist found with given wishlistName "+wishlistName;
    }
}
