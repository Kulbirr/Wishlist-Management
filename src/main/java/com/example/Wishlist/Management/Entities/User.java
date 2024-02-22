package com.example.Wishlist.Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String userName;

//    for validating password
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Password must contain at least one digit, one uppercase letter, one symbol, and no spaces"
    )
    private String password;

    @Column(unique = true)
//    @Size(min = 10, message = "Phone number should be at least 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number is invalid")
    private String phoneNo;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wishlist> wishlistList = new ArrayList<>();


}
