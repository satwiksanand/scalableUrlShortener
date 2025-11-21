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
    public ResponseEntity<RedirectResponse> redirectResponse(@PathVariable String shortUrl) throws Exception {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(urlService.redirectUrl(shortUrl));
    }

    @PostMapping("shorten")
    public ResponseEntity<ShortenResponse> shortenResponse(@RequestBody ShortenResponse resp){
        return ResponseEntity.ok(urlService.shortenUrl(resp.getLongUrl()));
    }
}
