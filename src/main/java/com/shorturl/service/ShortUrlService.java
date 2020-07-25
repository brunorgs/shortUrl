package com.shorturl.service;

import com.shorturl.dto.ShortUrlDto;
import com.shorturl.model.ShortUrl;
import com.shorturl.repository.ShortUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;

@Service
public class ShortUrlService implements IShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Override
    public ShortUrlDto create(String url, ZonedDateTime date) {

        if(StringUtils.isEmpty(url)) {
            return null;
        }

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setDate(date);
        shortUrl.setUrl(url);
        shortUrl.setId(RandomStringUtils.randomAlphanumeric(10));

        return toDto(shortUrlRepository.save(shortUrl));
    }

    public ShortUrlDto toDto(ShortUrl shortUrl) {

        ShortUrlDto shortUrlDto = new ShortUrlDto();

        String newUrl = String.join("/", "http://localhost:8080", shortUrl.getId());

        shortUrlDto.setUrl(newUrl);
        shortUrlDto.setDate(shortUrl.getDate());

        return shortUrlDto;
    }

    @Override
    public String find(String id) {

        if(StringUtils.isEmpty(id)) {
            return null;
        }

        ShortUrl shortUrl = shortUrlRepository.findById(id).orElse(null);

        if(shortUrl == null) {
            return null;
        }

        return shortUrl.getUrl();
    }
}
