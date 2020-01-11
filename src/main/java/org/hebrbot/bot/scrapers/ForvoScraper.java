package org.hebrbot.bot.scrapers;

import org.hebrbot.bot.utils.HttpUtils;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForvoScraper {

    public static final String BASE_HOST = "forvo.com";
    public static final Pattern INSIDE_PARENTHESES = Pattern.compile("\\(([^)]+)\\)");
    private static final String _SERVER_HOST = "forvo.com";
    private static final String _AUDIO_HTTP_HOST = "audio00.forvo.com";
    private String defaultProtocol = "https:";

    public String ab(String url) {
        return "https://" + BASE_HOST + url;
    }

    @SneakyThrows
    public String search(String word) {
        Document doc = Jsoup.parse(HttpUtils.getHtml(ab("/search/" + word)));
        final Element element = doc.select(".word").first();
        final Element node = element.parent().children().first();
        final String onclick = node.attr("onclick");
        String foundWord = element.text();
        Matcher m = INSIDE_PARENTHESES.matcher(onclick);
        if (m.find()) {
            String id, path_mp3, path_ogg, isMap, path_audio_mp3, path_audio_ogg, quality;
            final String[] strings = m.group(1).split(",");
            id = strings[0].replaceAll("'", "").trim();
            path_mp3 = strings[1].replaceAll("'", "").trim();
            path_ogg = strings[2].replaceAll("'", "").trim();
            isMap = strings[3].replaceAll("'", "").trim();
            path_audio_mp3 = strings[4].replaceAll("'", "").trim();
            path_audio_ogg = strings[5].replaceAll("'", "").trim();
            quality = strings[6].replaceAll("'", "").trim();
            final String link = getPlayLink(id, path_mp3, path_ogg, isMap, path_audio_mp3, path_audio_ogg, quality);
            return link;
        }
        return "";
    }

    private String getPlayLink(String id, String path_mp3, String path_ogg, String isMap, String path_audio_mp3, String path_audio_ogg, String quality) {
        String mp3 = defaultProtocol + "//" + _AUDIO_HTTP_HOST + "/mp3/" + base64_decode(path_mp3);
//            String path_ogg = defaultProtocol + '//' + _AUDIO_HTTP_HOST + '/ogg/' + base64_decode(path_ogg);
        return defaultProtocol + "//" + _AUDIO_HTTP_HOST + "/audios/mp3/" + base64_decode(path_audio_mp3);
    }

    private String base64_decode(String str) {
        return new String(Base64.decodeBase64(str.getBytes()));
    }
}
