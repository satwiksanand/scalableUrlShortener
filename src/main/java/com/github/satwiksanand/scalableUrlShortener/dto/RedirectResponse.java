package com.github.satwiksanand.scalableUrlShortener.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RedirectResponse {
    private String longUrl;
}
