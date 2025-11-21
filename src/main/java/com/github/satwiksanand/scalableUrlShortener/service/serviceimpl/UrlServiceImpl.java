package com.github.satwiksanand.scalableUrlShortener.service.serviceimpl;

import com.github.satwiksanand.scalableUrlShortener.service.UrlService;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Override
    public String shortenUrl(String longUrl) {
        return "";
    }

    @Override
    public String redirectUrl(String shortUrl) {
        return "";
    }
}
