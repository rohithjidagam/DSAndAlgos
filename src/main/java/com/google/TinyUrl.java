package com.google;

import java.util.HashMap;
import java.util.Map;

public class TinyUrl {

    Map<String, Integer> longMap = new HashMap<String, Integer>();
    Map<String, String> shortMap = new HashMap<>();
    static int counter = 1;
    static final String elemets = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {

        TinyUrl t = new TinyUrl();
        String url = "https://leetcode.com/problems/design-tinyurl/discuss/tinyurl";
        String shortUrl = t.longToShort(url);
        System.out.println(shortUrl);
        
        System.out.println(t.longMap);
        System.out.println(t.shortMap);

        String longUrl = t.shortToLong(shortUrl);
        System.out.println(longUrl);
    }

    private String shortToLong(String shortUrl) {

        shortUrl = shortUrl.substring("http://tiny.url/".length());
        int n = base62Tobase10(shortUrl);
        return shortMap.get(n);
    }

    private int base62Tobase10(String shortUrl) {

        int n = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            n = n * 62 + convert(shortUrl.charAt(i));
        }
        return n;
    }

    private int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }

    private String longToShort(String url) {
        String shortUrl = base10ToBase62(counter);
        longMap.put(url, counter);
        shortMap.put(shortUrl, url);
        counter++;
        return "http://tiny.url/" + shortUrl;
    }

    private String base10ToBase62(int n) {

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elemets.charAt(n % 62));
            n = n / 62;
        }

        while (sb.length() != 6) {
            sb.insert(0 ,"O");
        }
        return sb.toString();
    }

}

/*
 * N: Need (Assume the system is not massive if you are not sure) QPS (queries
 * per second) Daily User: 100M Daily usage per person: (Write) long2short 0.1,
 * (Read) short2long 1 Daily request: Write 10M, Read 100M QPS: Since a day is
 * 86400s approximately 100K. Write 100, Read 1K
 * 
 * Peak QPS: Write 200, Read 2K (Thousand level can be handled by a single SSD
 * MySQL Machine)
 * 
 * Storage 10M new mappings (long URL to short URL) per day assume each mapping
 * takes 100B in average 1GB every day. 1 TB hard drive could stand for 3 years.
 * Storage is not the problem for this kind of system. Service like Netflix may
 * have storage issues.
 * 
 * Through SN analysis, we could have a big picture of the system. In general,
 * this system is not hard and could be handled by a single SSD Machine.
 * 
 * A: API Only one service: URLService
 * 
 * Core (Business Logic) Layer: Class: URLService Interface:
 * URLService.encode(String long_url) URLService.decode(String short_url) Web
 * Layer: REST API: GET: /{short_url}, return a http redirect response(301)
 * POST: goo.gl method - google shorten URL Request Body: {url=longUrl} e.g.
 * {"longUrl": "http://www.google.com/"} Return OK(200), short_url is included
 * in the data
 * 
 * K: Data Access Step 1: Pick a storage structure SQL vs NoSQL? Does it need to
 * support transactions? NoSQL does not support transaction. Do we need rich SQL
 * query? NoSQL does not support as many queries as SQL. Pursue development
 * efficiency? Most Web Framework supports SQL database very well (with ORM). It
 * means fewer codes for the system. Do we need to use AUTO_INCREMENT ID? NoSQL
 * couldn't do this. It only has a global unique Object_id. Does the system has
 * a high requirement for QPS? NoSQL has high performance. For example,
 * Memcached's QPS could reach million level, MondoDB does 10K level, MySQL only
 * supports K level. How high is the system's scalability? SQL requires
 * developers write their codes to scale, while NoSQL comes with them (sharding,
 * replica). Answer: No -> NoSQL No -> NoSQL Doesn't matter because there are
 * only a few codes. -> NoSQL Our algorithm needs AUTO_INCREMENT ID. -> SQL
 * Write 200, Read 2K. Not high. -> SQL Not high. -> SQL
 */