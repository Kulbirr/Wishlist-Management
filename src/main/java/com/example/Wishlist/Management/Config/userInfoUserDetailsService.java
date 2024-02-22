package com.example.Wishlist.Management.Config;

import com.example.Wishlist.Management.Entities.User;
import com.example.Wishlist.Management.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        //returning the user details if the user is present in the database
        return user.map(userInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
