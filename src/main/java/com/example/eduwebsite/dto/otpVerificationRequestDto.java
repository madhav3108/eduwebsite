package com.example.eduwebsite.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class otpVerificationRequestDto {
    private String email;
    private String otp;
}
