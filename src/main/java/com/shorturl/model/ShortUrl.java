package com.shorturl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
public class ShortUrl {

    @Id
    private String id;

    private String url;

    private ZonedDateTime date;
}
