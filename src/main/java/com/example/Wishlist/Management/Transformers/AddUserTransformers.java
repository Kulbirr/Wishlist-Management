package com.example.Wishlist.Management.Transformers;

import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.Repositories.UserRepository;
import com.example.Wishlist.Management.RequestDTOS.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class AddUserTransformers {
    @Autowired
    private UserRepository userRepository;

    public static User convertAddUserRequestToUser(AddUserRequest addUserRequest){
        User userObj = User.builder()
                .userName(addUserRequest.getUserName())
                .phoneNo(addUserRequest.getPhoneNo())
                .password(addUserRequest.getPassword())
                .build();

        return userObj;
    }
}
