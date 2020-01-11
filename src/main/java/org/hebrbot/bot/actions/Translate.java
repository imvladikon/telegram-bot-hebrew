package org.hebrbot.bot.actions;

import org.hebrbot.bot.pojo.gresponse.Response;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Translate {

    Response response;

//    response.getDict().get(0) - terms, entry - another translation

    //TODO: add translate direction
    @SneakyThrows
    public String get(String word) {
        String url = "https://clients5.google.com/translate_a/t?client=dict-chrome-ex&sl=auto&tl=he&q="+word;
        this.response = getResponse(url);
        if(response.getSentences().size()>0) {
            return response.getSentences().get(0).getTrans();
        }
        return "";
    }

    public static Response getResponse(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "clients5.google.com");
        headers.set("Connection", "keep-alive");
        headers.set("Upgrade-Insecure-Requests", "1");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.set("Sec-Fetch-Site", "cross-site");
        headers.set("Sec-Fetch-Mode", "navigate");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept-Language", "en-US,en;q=0.9,ru;q=0.8,he;q=0.7");
        Map<String, String> vars = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        restTemplate.getMessageConverters()
//                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.getMessageConverters().add(1, new MappingJacksonHttpMessageConverter());
        HttpEntity entity = new HttpEntity(headers);
        final ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                Response.class, vars);
        return responseEntity.getBody();

    }
}
