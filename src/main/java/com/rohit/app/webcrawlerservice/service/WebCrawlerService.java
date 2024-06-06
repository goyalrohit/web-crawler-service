package com.rohit.app.webcrawlerservice.service;

import com.rohit.app.webcrawlerservice.jms.producer.ClientNotificationAmqProducer;
import com.rohit.app.webcrawlerservice.util.CrawlerUtil;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Log4j2
@Service
public class WebCrawlerService {

    @Autowired
    private ClientNotificationAmqProducer clientNotificationAmqProducer;

    private HashSet<String> urls;
    private String baseDomain;

    public WebCrawlerService() {
        urls = new HashSet<String>();
    }

    public Set<String> crawl(String URL) {
        if (Objects.isNull(baseDomain))
            this.baseDomain = CrawlerUtil.getDomainName(URL);
        return crawlInternal(URL);
    }

    private Set<String> crawlInternal(String URL) {
        //check whether we have already crawled the URL or not.
        if (!urls.contains(URL) && CrawlerUtil.isSameDomain(URL, baseDomain)) {
            try {
                if (urls.add(URL)) {
                    log.debug("Crawled URL " + URL);
                    // send response to the client
                    clientNotificationAmqProducer.send(URL);
                }
                // fetch HTML for URL
                Document doc = Jsoup.connect(URL).get();
                // parse the HTML code for extracting links of URLs
                Elements availableLinksOnPage = doc.select("a[href]");
                for (Element ele : availableLinksOnPage) {
                    String internalUrl = ele.attr("abs:href");
                    if (CrawlerUtil.isSameDomain(internalUrl,baseDomain)) {
                        crawl(ele.attr("abs:href"));
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
        return urls;

    }
}

