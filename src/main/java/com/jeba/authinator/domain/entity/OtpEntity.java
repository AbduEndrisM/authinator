package com.jeba.authinator.domain.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("otp")
public class OtpEntity {
    @Id
    private String id;
    @JsonProperty("to")
    private String to;
    @JsonProperty("from")
    private String from;
    @JsonProperty("otp_code")
    private int otpCode;
    @JsonProperty("expiry_time")
    private Instant expiryTime;
    @JsonProperty(value = "verified")
    private Boolean verified = false;

}
