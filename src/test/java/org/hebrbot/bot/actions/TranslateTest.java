package org.hebrbot.bot.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hebrbot.bot.pojo.gresponse.Response;
import lombok.SneakyThrows;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;


//import jdk.incubator.http.*;

public class TranslateTest {

    @Test
    @SneakyThrows
    public void get1() {
        final String s = new Translate().get("go");
        final Response response = new ObjectMapper().readValue(s, Response.class);
        System.out.println(response.getSentences().get(0).getTrans());
    }

    @Test
    @SneakyThrows
    public void get2() {
    }

    public void write(String text) {
        String w = text;
    }

    @Test
    @SneakyThrows
    public void get3() {
        String url = "https://clients5.google.com/translate_a/t?client=dict-chrome-ex&sl=auto&tl=he&q=собака";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Host", "clients5.google.com");
        headers.set("Connection", "keep-alive");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        ResponseEntity<Map> response = restTemplate.exchange("https://httpbin.org/get", HttpMethod.GET, entity, Map.class);
//        user-agent
        ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, entity, Response.class);
        System.out.println(response.getBody().getSentences());
    }
}