package com.github.satwiksanand.scalableUrlShortener.modals;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
@Data
public class Urls {
    @Id
    private String id;

    @NotNull(message = "short url cannot be null")
    @NotEmpty(message = "short url cannot be empty")
    private String shortUrl;

    @NotNull(message = "short url cannot be null")
    @NotEmpty(message = "short url cannot be empty")
    private String longUrl;

    @CreatedDate
    private LocalDateTime createdAt;
}
