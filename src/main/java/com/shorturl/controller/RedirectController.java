package com.shorturl.controller;

import com.shorturl.service.IShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RedirectController {

    @Autowired
    private IShortUrlService shortUrlService;


    @GetMapping("{id}")
    public RedirectView redirect(@PathVariable("id") String id) {
        String url = shortUrlService.find(id);
        return new RedirectView(url);
    }
}
