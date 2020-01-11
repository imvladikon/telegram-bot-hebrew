package org.hebrbot.bot.scrapers;

import org.hebrbot.bot.utils.HttpUtils;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MorfixScraper {
    public static final String BASE_HOST = "www.morfix.co.il";

    public String ab(String url) {
        return "https://" + BASE_HOST + url;
    }

    @SneakyThrows
    public String search(String word) {
        final Document doc = Jsoup.parse(HttpUtils.getHtml(ab("/"+word)));
        return doc.select(".normal_translation_div").first().text();
    }
}
