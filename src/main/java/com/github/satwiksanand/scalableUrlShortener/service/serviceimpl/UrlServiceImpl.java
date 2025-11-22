package com.github.satwiksanand.scalableUrlShortener.service.serviceimpl;

import com.github.satwiksanand.scalableUrlShortener.dto.RedirectResponse;
import com.github.satwiksanand.scalableUrlShortener.dto.ShortenResponse;
import com.github.satwiksanand.scalableUrlShortener.mappers.RedirectResponseMapper;
import com.github.satwiksanand.scalableUrlShortener.mappers.ShortenResponseMapper;
import com.github.satwiksanand.scalableUrlShortener.modals.Urls;
import com.github.satwiksanand.scalableUrlShortener.repository.UrlRepository;
import com.github.satwiksanand.scalableUrlShortener.service.UrlService;
import com.github.satwiksanand.scalableUrlShortener.utils.GenerateShortUrl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final int SHORT_URL_LENGTH = 8;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ShortenResponse shortenUrl(String longUrl) {
        //for now i am using a 8 character sequence, i will modify it later think of it as something necessary at the moment.
        //we are using random strategy for short url generation at the moment.
        Optional<Urls> existing = urlRepository.findByLongUrl(longUrl);
        if (existing.isPresent()) {
            return ShortenResponseMapper.toDto(existing.get());
        }
        String shortUrl = null;
        do {
            shortUrl = GenerateShortUrl.generateRandomShortUrl(longUrl, SHORT_URL_LENGTH);
        } while (urlRepository.existsByShortUrl(shortUrl));
        Urls newUrls = new Urls();
        newUrls.setLongUrl(longUrl);
        newUrls.setShortUrl(shortUrl);
        Urls savedUrl = urlRepository.save(newUrls);
        return ShortenResponseMapper.toDto(savedUrl);
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
