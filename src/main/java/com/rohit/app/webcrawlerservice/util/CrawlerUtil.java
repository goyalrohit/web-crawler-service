package com.rohit.app.webcrawlerservice.util;

import java.net.URI;
import java.net.URISyntaxException;

public class CrawlerUtil {

    public static boolean isSameDomain(String url, String baseDomain) {
        String domain = getDomainName(url);
        return domain.contains(baseDomain);
    }

    public static String getDomainName(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

       /*private String getBaseDomain(String url) {
        try {
            URL u = new URL(url);
            String host = u.getHost();
            int index = host.indexOf(".");
            if (index != -1) {
                return host.substring(index + 1);
            } else {
                return host;
            }
        } catch (IOException e) {
            return "";
        }
    }*/
}
