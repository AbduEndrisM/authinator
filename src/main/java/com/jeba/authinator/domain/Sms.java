package com.jeba.authinator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Sms {

    @JsonProperty("message")
    private String message;

    @NotNull @NotBlank
    @JsonProperty("phone_number")
    private String phoneNumber;


}
