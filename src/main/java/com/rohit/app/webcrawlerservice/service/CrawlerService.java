package com.rohit.app.webcrawlerservice.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CrawlerService {
        private final String seedUrl;
        private final int maxPages;
        private final Set<String> visited;
        private final Queue<String> queue;

        public CrawlerService(String seedUrl, int maxPages) {
            this.seedUrl = seedUrl;
            this.maxPages = maxPages;
            this.visited = new HashSet<>();
            this.queue = new LinkedList<>();
            this.queue.add(seedUrl);
        }

        public void crawl() {
            while (!queue.isEmpty() && visited.size() < maxPages) {
                String url = queue.poll();
                if (!visited.contains(url)) {
                    visited.add(url);
                    System.out.println(url); // Print the URL (you can modify this to build a sitemap)

                    try {
                        Document doc = Jsoup.connect(url).get();
                        Elements links = doc.select("a[href]");
                        for (Element link : links) {
                            String nextUrl = link.absUrl("href");
                            if (isSameDomain(nextUrl)) {
                                queue.add(nextUrl);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error fetching " + url + ": " + e.getMessage());
                    }
                }
            }
        }

        private boolean isSameDomain(String url)  {
            try {
                return url.contains(getDomainName(seedUrl));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

        public static void main(String[] args) {
            String seedUrl = "https://redhat.com/en";
            int maxPages = 100;
            CrawlerService crawler = new CrawlerService(seedUrl, maxPages);
            crawler.crawl();
        }
    }

