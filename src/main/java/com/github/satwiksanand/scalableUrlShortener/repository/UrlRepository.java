package com.github.satwiksanand.scalableUrlShortener.repository;

import com.github.satwiksanand.scalableUrlShortener.modals.Urls;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Urls, String> {
    boolean existsByShortUrl(String shortUrl);

    Optional<Urls> findByShortUrl(String shortUrl);
}
