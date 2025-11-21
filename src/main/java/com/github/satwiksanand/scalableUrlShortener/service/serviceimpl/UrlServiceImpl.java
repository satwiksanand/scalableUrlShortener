package com.github.satwiksanand.scalableUrlShortener.service.serviceimpl;

import com.github.satwiksanand.scalableUrlShortener.dto.RedirectResponse;
import com.github.satwiksanand.scalableUrlShortener.dto.ShortenResponse;
import com.github.satwiksanand.scalableUrlShortener.mappers.RedirectResponseMapper;
import com.github.satwiksanand.scalableUrlShortener.modals.Urls;
import com.github.satwiksanand.scalableUrlShortener.repository.UrlRepository;
import com.github.satwiksanand.scalableUrlShortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ShortenResponse shortenUrl(String longUrl) {
        return null;
    }

    @Override
    public RedirectResponse redirectUrl(String shortUrl) throws Exception {
        Optional<Urls> url = urlRepository.findByShortUrl(shortUrl);
        if(url.isPresent()){
            return RedirectResponseMapper.toDto(url.get());
        }
        throw new Exception("url does not exists");
    }
}
