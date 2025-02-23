package com.example.eduwebsite.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDto {


    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed!")
    private String FirstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed!")
    private String LastName;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
    private String Email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters long")
    private String Password;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo; // Changed to camelCase


    private List<AddressDto> Addresses;



}

