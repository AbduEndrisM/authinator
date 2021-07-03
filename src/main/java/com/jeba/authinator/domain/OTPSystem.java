package com.jeba.authinator.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPSystem {

    @JsonProperty("otp_code")
    int otpCode;

    PhoneNumber phoneNumber;

    @JsonProperty("expiry_time")
    String expiryTime;
}
