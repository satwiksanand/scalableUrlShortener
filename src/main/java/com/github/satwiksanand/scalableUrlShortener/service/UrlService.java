package com.github.satwiksanand.scalableUrlShortener.service;

public interface UrlService {
    String shortenUrl(String longUrl);
    String redirectUrl(String shortUrl);
}
