package com.github.satwiksanand.scalableUrlShortener.service;

public interface RateLimiterService {
    boolean isAllowed(String ip);
}
