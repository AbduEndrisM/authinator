package com.jeba.authinator.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private String id;
    @JsonProperty("phone_number")
    private String phoneNumber;
}