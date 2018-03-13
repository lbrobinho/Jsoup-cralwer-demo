package com.Bo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class Main {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0";
    //private static final String USER_AGENT2 =
    public static void main(String[] args) throws IOException {
	// write your code here dsdsd
        List<String> queryList = new ArrayList<>();
        queryList.add("Real Madird");
        queryList.add("wall Mount Shelf");
        queryList.add("building toys");


        for(int i = 0; i < queryList.size(); i++) {
            String requestUrl = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=" + queryList.get(i);
            Document doc = Jsoup.connect(requestUrl).userAgent(USER_AGENT).timeout(10000).get();

            List<String> urlPath = new ArrayList<>();
            urlPath.add("#result_2 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a");
            urlPath.add("#rot-B078NSNV4L > div > div.s-card.s-card-group-rot-B078NSNV4L.s-active > a");

            for(int j = 0; j < urlPath.size(); j++) {
                Element ele = doc.select(urlPath.get(j)).first();
                if (ele != null) {
                    String detailUrl = ele.attr("href");
                    System.out.println("detail url = " + detailUrl);
                    String title = ele.attr("title");
                    System.out.println("title = " + title);
                    break;
                }
            }

            String imagePath = "#result_2 > div > div > div > div.a-fixed-left-grid-col.a-col-left > div > div > a > img";
            Element imgEle = doc.select(imagePath).first();
            if(imgEle != null) {
                String imgUrl = imgEle.attr("src");
                System.out.println("img url = " + imgUrl);
            }

            String pricePath = "#result_3 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span.a-offscreen";
            Element priceEle = doc.select(pricePath).first();

            if(priceEle != null) {
                String priceUrl = priceEle.attr("aria-label");
                System.out.println("price url = " + priceUrl);
            }
        }
    }
}
