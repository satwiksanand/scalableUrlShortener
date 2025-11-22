package com.github.satwiksanand.scalableUrlShortener.controller;

import com.github.satwiksanand.scalableUrlShortener.dto.RedirectResponse;
import com.github.satwiksanand.scalableUrlShortener.dto.ShortenResponse;
import com.github.satwiksanand.scalableUrlShortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("urls/{shortUrl}")
    public ResponseEntity<Void> redirectResponse(@PathVariable String shortUrl) throws Exception {
        RedirectResponse response = urlService.redirectUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(java.net.URI.create(response.getLongUrl()))
                .build();
    }

    @PostMapping("shorten")
    public ResponseEntity<ShortenResponse> shortenResponse(@RequestBody ShortenResponse resp) {
        return ResponseEntity.ok(urlService.shortenUrl(resp.getLongUrl()));
    }
}
