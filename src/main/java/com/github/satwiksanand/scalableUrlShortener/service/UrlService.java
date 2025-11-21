package com.github.satwiksanand.scalableUrlShortener.service;

import com.github.satwiksanand.scalableUrlShortener.dto.RedirectResponse;
import com.github.satwiksanand.scalableUrlShortener.dto.ShortenResponse;

public interface UrlService {
    ShortenResponse shortenUrl(String longUrl);
    RedirectResponse redirectUrl(String shortUrl) throws Exception;
}
