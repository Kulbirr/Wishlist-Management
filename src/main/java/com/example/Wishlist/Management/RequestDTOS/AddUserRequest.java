package com.example.Wishlist.Management.RequestDTOS;

import com.example.Wishlist.Management.Entities.Wishlist;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddUserRequest {

    private String userName;

    private String Password;

    private String phoneNo;


}
