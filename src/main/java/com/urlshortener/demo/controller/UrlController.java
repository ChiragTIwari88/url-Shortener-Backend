
package com.urlshortener.demo.controller;

import com.urlshortener.demo.dto.CreateUrlRequest;
import com.urlshortener.demo.entity.Url;
import com.urlshortener.demo.service.UrlService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public Map<String, String> createUrl(
            @RequestBody CreateUrlRequest request) {

        Url savedUrl = urlService.createShortUrl(
                request.getOriginalUrl()
        );

        String shortUrl =
                "http://localhost:8080/" + savedUrl.getShortCode();

        return Map.of(
                "originalUrl", savedUrl.getOriginalUrl(),
                "shortCode", savedUrl.getShortCode(),
                "shortUrl", shortUrl
        );
    }
}
