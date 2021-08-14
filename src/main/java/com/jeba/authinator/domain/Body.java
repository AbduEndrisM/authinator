package com.jeba.authinator.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Document(collection = "message")
public class Body {
    @Id
    private String message;
    private Instant messageTime;
}

