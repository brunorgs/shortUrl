package com.shorturl.controller;

import com.shorturl.dto.ShortUrlDto;
import com.shorturl.model.ShortUrl;
import com.shorturl.service.IShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    @Autowired
    private IShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<ShortUrlDto> createShortLink(@RequestBody ShortUrlDto shortUrlDto) {
        ShortUrl shortUrl = shortUrlService.toEntity(shortUrlDto);
        return new ResponseEntity<>(shortUrlService.create(shortUrl), HttpStatus.OK);
    }
}
