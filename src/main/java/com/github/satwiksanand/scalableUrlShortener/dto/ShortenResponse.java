package com.github.satwiksanand.scalableUrlShortener.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortenResponse {
    private String id;
    private String shortUrl;
    private String longUrl;
}
