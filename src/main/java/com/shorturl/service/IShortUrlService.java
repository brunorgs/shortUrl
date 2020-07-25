package com.shorturl.service;

import com.shorturl.dto.ShortUrlDto;
import com.shorturl.model.ShortUrl;

public interface IShortUrlService {

    ShortUrlDto create(ShortUrl shortUrl);

    ShortUrl toEntity(ShortUrlDto shortUrlDto);

    String find(String id);
}
