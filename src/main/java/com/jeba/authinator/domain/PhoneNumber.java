package com.jeba.authinator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {

    @JsonProperty("phone_number")
    @NotNull @NotBlank
    private String phoneNumber;


}
