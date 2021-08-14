package com.jeba.authinator.domain.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPVerifyPayload {

    private String phoneNumber;
    private int otpCode;

}
