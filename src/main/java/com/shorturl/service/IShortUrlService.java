package com.shorturl.service;

import com.shorturl.dto.ShortUrlDto;

import java.time.ZonedDateTime;

public interface IShortUrlService {

    ShortUrlDto create(String url, ZonedDateTime date);

    String find(String id);
}
