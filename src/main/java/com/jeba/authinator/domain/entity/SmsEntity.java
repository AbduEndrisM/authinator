package com.jeba.authinator.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeba.authinator.domain.Body;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("sms")
public class SmsEntity {

    @Id
    private String id;

    @NotNull
    @NotBlank
    @JsonProperty("to")
    private String to;
    @JsonProperty("from")
    private String from;
    @JsonProperty("message")
    @NotNull
    @NotBlank
    private String message;
    @JsonProperty("sent_time")
    private Instant sentTime;
    @JsonProperty("description")
    private String description;

    //    @JsonProperty("message")
//    @DBRef
//    private List<Body> message = new ArrayList<>();


}
