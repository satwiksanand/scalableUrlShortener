package com.github.satwiksanand.scalableUrlShortener.mappers;

import com.github.satwiksanand.scalableUrlShortener.dto.RedirectResponse;
import com.github.satwiksanand.scalableUrlShortener.modals.Urls;

public class RedirectResponseMapper {
    public static RedirectResponse toDto(Urls urls){
        return RedirectResponse.builder()
                .longUrl(urls.getLongUrl())
                .build();
    }
}
