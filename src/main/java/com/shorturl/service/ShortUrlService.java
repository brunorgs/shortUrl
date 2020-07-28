package com.shorturl.service;

import com.shorturl.dto.ShortUrlDto;
import com.shorturl.model.ShortUrl;
import com.shorturl.repository.ShortUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@Service
public class ShortUrlService implements IShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Value("${URL_BACKEND}")
    private String urlBackend;

    @Override
    public ShortUrlDto create(ShortUrl shortUrl) {

        if(StringUtils.isEmpty(shortUrl.getUrl())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url is null or empty");
        }

        if(shortUrl.getDate() == null) {
            shortUrl.setDate(ZonedDateTime.now().plusYears(5));
        }

        if(shortUrl.getDate().isBefore(ZonedDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Date");
        }

        shortUrl.setId(RandomStringUtils.randomAlphanumeric(10));

        return toDto(shortUrlRepository.save(shortUrl));
    }

    public ShortUrlDto toDto(ShortUrl shortUrl) {

        ShortUrlDto shortUrlDto = new ShortUrlDto();

        String newUrl = String.join("/", urlBackend, shortUrl.getId());

        shortUrlDto.setUrl(newUrl);
        shortUrlDto.setDate(shortUrl.getDate());

        return shortUrlDto;
    }

    @Override
    public ShortUrl toEntity(ShortUrlDto shortUrlDto) {

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setDate(shortUrlDto.getDate());
        shortUrl.setUrl(shortUrlDto.getUrl());

        return shortUrl;
    }

    @Override
    public String find(String id) {

        if(StringUtils.isEmpty(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is null");
        }

        ShortUrl shortUrl = shortUrlRepository.findById(id).orElse(null);

        if(shortUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url does not exists");
        }

        if(shortUrl.getDate().isBefore(ZonedDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link expired");
        }

        return shortUrl.getUrl();
    }
}
