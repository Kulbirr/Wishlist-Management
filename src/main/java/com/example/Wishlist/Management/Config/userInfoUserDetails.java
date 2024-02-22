package com.example.Wishlist.Management.Config;

import com.example.Wishlist.Management.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class userInfoUserDetails implements UserDetails {
    private User user;

    private String phoneNo;

    private String password;

    private String userName;

    public userInfoUserDetails(User user) {
        this.phoneNo = user.getPhoneNo();
        this.password = user.getPassword();
        this.userName = user.getUserName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

//    user account is never expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    user account is never locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//    user credentials are never expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    user is always enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
