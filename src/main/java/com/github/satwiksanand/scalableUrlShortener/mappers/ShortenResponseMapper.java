package com.github.satwiksanand.scalableUrlShortener.mappers;

import com.github.satwiksanand.scalableUrlShortener.dto.ShortenResponse;
import com.github.satwiksanand.scalableUrlShortener.modals.Urls;

public class ShortenResponseMapper {
    public static ShortenResponse toDto(Urls urls){
        return ShortenResponse.builder()
                .id(urls.getId())
                .longUrl(urls.getLongUrl())
                .shortUrl(urls.getShortUrl())
                .build();
    }
}
