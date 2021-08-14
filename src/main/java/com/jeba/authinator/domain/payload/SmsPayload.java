package com.jeba.authinator.domain.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SmsPayload {

    @NotNull
    @NotBlank
    @JsonProperty("to")
    private String to;
    @JsonProperty("from")
    private String from;
    @JsonProperty("message")
    private String message;
    @JsonProperty("description")
    private String description;

//    @JsonProperty("message")
//    @DBRef
//    private List<Body> message = new ArrayList<>();

}
