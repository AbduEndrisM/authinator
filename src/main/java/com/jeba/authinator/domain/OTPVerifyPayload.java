package com.jeba.authinator.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPVerifyPayload {
    @JsonProperty("otp_code")
    int otpCode;
    @JsonProperty("phone_number")
    PhoneNumber phoneNumber;
}
