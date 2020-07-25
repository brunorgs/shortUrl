package com.shorturl.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ShortUrlDto implements Serializable {

    private String url;

    private ZonedDateTime date;
}
