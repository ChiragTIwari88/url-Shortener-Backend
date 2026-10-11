package com.urlshortener.demo.service;

import com.urlshortener.demo.entity.Url;
import com.urlshortener.demo.repository.UrlRepository;
import com.urlshortener.demo.util.ShortCodeGenerator;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createShortUrl(String originalUrl) {

        String shortCode;

        do {
            shortCode = ShortCodeGenerator.generateCode();
        } while (urlRepository.findByShortCode(shortCode).isPresent());

        Url url = new Url();

        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setCreatedAt(LocalDateTime.now());
        url.setClickCount(0L);

        return urlRepository.save(url);
    }
}
