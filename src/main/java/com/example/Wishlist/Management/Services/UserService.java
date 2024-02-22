package com.example.Wishlist.Management.Services;

import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.Repositories.UserRepository;
import com.example.Wishlist.Management.RequestDTOS.AddUserRequest;
import com.example.Wishlist.Management.Transformers.AddUserTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String addUser(AddUserRequest addUserRequest) {
        //checking if the user already exists in the database
        Optional<User> newUser = userRepository.findByUserName(addUserRequest.getUserName());
        if(newUser.isPresent()){
            throw new IllegalStateException("User already exists");
        }
        //converting the request to user object and saving it to the database
        User user = AddUserTransformers.convertAddUserRequestToUser(addUserRequest);
        user.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        userRepository.save(user);
        return "Registered Successfully!";
    }
}
