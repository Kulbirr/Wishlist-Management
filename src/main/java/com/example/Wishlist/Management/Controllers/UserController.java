package com.example.Wishlist.Management.Controllers;

import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.RequestDTOS.AddUserRequest;
import com.example.Wishlist.Management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;

//    publicly accessible endpoint
    @GetMapping("home")
    public String home(){
        return "Home Page";
    }

    @PostMapping("register")
    public ResponseEntity saveUser(@RequestBody AddUserRequest addUserRequest){
        String result = userService.addUser(addUserRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



}

