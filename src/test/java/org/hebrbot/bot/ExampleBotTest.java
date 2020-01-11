package org.hebrbot.bot;

import lombok.SneakyThrows;
import org.hebrbot.bot.model.HebrewVerb;
import org.hebrbot.bot.scrapers.ConjugationRow;
import org.hebrbot.bot.scrapers.PealimScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import java.util.Map;

import static org.hebrbot.bot.utils.HttpUtils.getHtml;
import static org.hebrbot.bot.scrapers.PealimScraper.getVerbCategories;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampleBotApplication.class)
public class ExampleBotTest {

    //TODO: proxy rest server
    @org.junit.Test
    public void testHttpClientWebClient() {

//        Flux<Tweet> tweetFlux = WebClient.create()
//                .get()
//                .uri(getSlowServiceUri())
//                .retrieve()
//                .bodyToFlux(Tweet.class);
//        tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
        System.out.println("Async proof");
    }

//    @SneakyThrows
//    @org.junit.Test
//    public void testHttpClient() {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://opensky-network.org/api/states/all?extended=true"))
//                .timeout(Duration.ofMinutes(2))
//                .header("Content-Type", "application/json")
//                .GET()
////                 .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
//                .build();
//        final HttpClient client = HttpClient.newHttpClient();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(ExampleBotTest::getLine);
//
//    }

    public static void getLine(String x) {
        String html = x;
    }



    @SneakyThrows
    @org.junit.Test
    public void milog() {
        String url = "http://milog.co.il/%D7%9C%D6%B0%D7%94%D6%B4%D7%AA%D6%B0%D7%90%D6%B7%D7%91%D6%BC%D6%B5%D7%93";
        Document doc = Jsoup.parse(getHtml(url));
        Element element = doc.selectFirst(".sr_res");
        System.out.println(element.text());
    }

    @SneakyThrows
    @org.junit.Test
    public void pealimSearchWord() {
        String word = "להתאבד";
        String url = "https://www.pealim.com/search/?q=" + word;
        String html = getHtml(url);
        Document doc = Jsoup.parse(html);
        final Elements el_root = doc.select(".verb-search-root");
        final Elements el_binyan = doc.select(".verb-search-binyan");
        final Elements el_meaning = doc.select(".verb-search-meaning");
        final String href = doc.select(".verb-search-button a").attr("href");
    }

    @Test
    public void search3() {
        PealimScraper p = new PealimScraper();
        final HebrewVerb verb = p.search("לְתַגְמֵל");
    }

    @SneakyThrows
    @org.junit.Test
    public void pealimDict() {
        String url = "https://www.pealim.com/dict/?page=1";
        String html = getHtml(url);
        Document doc = Jsoup.parse(html);
        String last_page_url = doc.select(".pagination li:last-of-type a").attr("href");
        Elements dict = doc.select(".dict-table-t");
        for (Element element : dict.select("tr")) {
            String href = element.select("td:first-of-type").select("a").attr("href");
            for (Element cell : element.select("td")) {
                System.out.println(cell.text());
                cell.select(".menukad");
                cell.select(".dict-transcription");
                cell.select(".dict-meaning");
            }
        }
    }

    @SneakyThrows
    @org.junit.Test
    public void pealim() {
        String url = "https://www.pealim.com/dict/59-lehitabed/";
        String html = getHtml(url);
        Document doc = Jsoup.parse(html);
        final Elements table = doc.select(".conjugation-table");
        for (Map.Entry<String, ConjugationRow> row : getVerbCategories().entrySet()) {
            String categoryName = row.getKey();
            final Elements elements = table.select("#" + categoryName);
//            System.out.println(elements.text());
            System.out.println(elements.select(".menukad").text());
            System.out.println(elements.select(".transcription").text());
            System.out.println(elements.select(".meaning").text());
        }
        Elements related_words_node = doc.select(".dict-table-t");
        for (Element element : related_words_node.select("tr")) {
            for (Element cell : element.select("td")) {
                System.out.println(cell.text());
                cell.select(".menukad");
                cell.select(".dict-transcription");
                cell.select(".dict-meaning");
            }
        }
    }
}