package org.hebrbot.bot.scrapers;

import org.hebrbot.bot.utils.HttpUtils;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

public class ReversoScraper {
    public static final String BASE_HOST = "context.reverso.net";

    public String ab(String url) {
        return "https://" + BASE_HOST + url;
    }

    @SneakyThrows
    public List<String> search(String word) {
        Document doc = Jsoup.parse(HttpUtils.getHtml(ab("/translation/english-hebrew/" + word)));
        return doc.select(".example").stream().limit(3).map(element ->
                element.select(".src").select(".text").text() + " " +
                        element.select(".trg").select(".text").text() + "\n "
        ).collect(Collectors.toList());
    }

}
