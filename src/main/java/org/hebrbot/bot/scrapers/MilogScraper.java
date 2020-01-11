package org.hebrbot.bot.scrapers;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hebrbot.bot.utils.HttpUtils;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

public class MilogScraper {

	public static final String BASE_HOST = "milog.co.il";

	//audio = https://milog.co.il/tts/sound/misc/{nameNekudot}.mp3
	public String ab(String url) {
		return "https://" + BASE_HOST + url;
	}

	@SneakyThrows
	public String search(String word) {
		final String html = HttpUtils.getHtml(ab("/" + word));
		Document doc = Jsoup.parse(html);
		final Element element = doc.select(".sr_main").select(".sr_e").first();
		//div - sound + part of speech
		//div[@class=='sr_e_para']
		return element.text();
	}

	public String audio(String wordNekudot) {
		final String url = ab("/tts/sound/misc/" + wordNekudot + ".mp3");
		CloseableHttpClient httpClient =
				HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> result =
				restTemplate.exchange(url, HttpMethod.HEAD, entity, String.class);
		if (result.getStatusCodeValue() >= HttpURLConnection.HTTP_OK
			&& result.getStatusCodeValue() < HttpURLConnection.HTTP_MULT_CHOICE) {
			//            return result.getHeaders().getContentLength()
		}
		return url;
	}

	//    function click_sound_file(text, taatik) {
	//        let url = "https://milog.co.il/ajax_get_tts.php";
	//        let  data = { text: text, taatik: taatik };
	//
	//        $.ajax({
	//                url: url,
	//                type: "POST",
	//                contentType: "application/json",
	//                data: JSON.stringify(data),
	//                error: function error(data) {
	//                console.log("error: " + data.responseText);
	//        },
	//        success: function success(data) {
	//                console.log(data);
	//        console.log(data.error);
	//        if (data.error === 0) {
	//            console.log("Playing");
	//            var audio = new Audio(data.filename);
	//            audio.play();
	//        }
	//        }
	//    });

}
