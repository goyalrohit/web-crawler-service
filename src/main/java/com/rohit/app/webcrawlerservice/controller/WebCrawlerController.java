package com.rohit.app.webcrawlerservice.controller;

import com.rohit.app.webcrawlerservice.service.WebCrawlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class WebCrawlerController {
    private final WebCrawlerService webCrawlerService;
    public WebCrawlerController(WebCrawlerService webCrawlerService) {
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping("/crawl")
    public Set<String> crawl(@RequestParam String url) {
        System.out.println("Crawling " + url);
        return webCrawlerService.crawl(url);
    }
}