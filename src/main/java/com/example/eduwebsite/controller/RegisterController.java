
package com.example.eduwebsite.controller;

import com.example.eduwebsite.dao.UserRepository;
import com.example.eduwebsite.dto.RegisterDto;
import com.example.eduwebsite.entity.Address;
import com.example.eduwebsite.entity.User;
import com.example.eduwebsite.dto.LoginRequestDto;
import com.example.eduwebsite.service.RegisterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterDto registerDto) {
        log.info("Enter controller");
        try {
            registerService.initiateRegistration(registerDto);
            log.info("controller successfully");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registration successful. An OTP has been sent to your email.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verify(@RequestParam String email, @RequestParam String otp) {
        try {
            boolean isVerified = registerService.completeRegistration(email, otp);
            if (isVerified) {
                return ResponseEntity.ok("OTP verification successful");
            } else {
                // Check if the user exists and provide a more informative message
                if (!userRepository.findByEmail(email).isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP or user not verified");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            User user = registerService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("Login Successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/search/users")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {
        List<User> users = registerService.searchUsers(name, email, phone);
        return ResponseEntity.ok(users);
    }

    // Search addresses by city, state, or country
    @GetMapping("/search/addresses")
    public ResponseEntity<List<Address>> searchAddresses(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country) {
        List<com.example.eduwebsite.entity.Address> addresses = registerService.searchAddresses(city, state, country);
        return ResponseEntity.ok(addresses);
    }

//

}
