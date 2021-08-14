package com.jeba.authinator.domain.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OtpRequestPayload {
    @JsonProperty("to")
    private String to;
    @JsonProperty("from")
    private String from;
    @JsonProperty("otp_code")
    private int otpCode;
}
