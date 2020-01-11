package org.hebrbot.bot.utils;

import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
	public static String getHtml(String url) throws MalformedURLException {
		URL u = new URL(url);
		Map<String, String> vars = new HashMap<>();
		Map<String, String> headers = new HashMap<>();
		headers.put("Host", u.getHost());
		headers.put("Connection", "keep-alive");
		headers.put("Accept", "*/*");
		headers.put("Origin", url);
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
		//        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.84 Mobile Safari/537.36");
		//        headers.put("Content-Type", "application/json");
		headers.put("Referer", url);
		headers.put("Accept-Language", "en-US,en;q=0.9,ru;q=0.8,he;q=0.7");
		CloseableHttpClient httpClient =
				HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getMessageConverters()
				.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate.getForObject(url, String.class, vars, headers);

	}

	public static String postJSONData(String url, String data) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; encoding='utf-8'");
		con.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
		con.getOutputStream().write(data.getBytes());

		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			throw new HttpResponseException(responseCode, con.getResponseMessage());
		}
	}

}
