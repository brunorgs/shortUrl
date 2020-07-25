package com.shorturl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
public class ShortUrl {

    @Id
    private String id;

    @Lob
    private String url;

    private ZonedDateTime date;
}
