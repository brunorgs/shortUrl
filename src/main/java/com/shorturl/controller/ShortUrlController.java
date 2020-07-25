package com.shorturl.controller;

import com.shorturl.service.IShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    @Autowired
    private IShortUrlService shortUrlService;

    @PostMapping("/")
    public ResponseEntity<Object> createShortLink(@RequestParam("url") String url, @RequestParam("date") ZonedDateTime date) {
        return new ResponseEntity<>(shortUrlService.create(url, date), HttpStatus.CREATED);
    }
}
