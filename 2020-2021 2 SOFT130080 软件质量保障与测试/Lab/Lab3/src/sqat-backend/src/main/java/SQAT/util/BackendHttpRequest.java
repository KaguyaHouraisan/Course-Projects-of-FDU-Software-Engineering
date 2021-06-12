package SQAT.util;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BackendHttpRequest {
	public static String backendPost(String url, Map<String, Object> requestBody, HttpHeaders headers) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> response;

		try {
			response = restTemplate.postForEntity(url, httpEntity, String.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public static String backendGet(String url, HttpHeaders headers) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response;

		try {
			response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public static String sendGet(String url, String param, String token) {
		String result = "";
		BufferedReader bufferedReader = null;
		try {
			String urlNameString = url;
			if (!param.equals("")) {
				urlNameString = url + "?" + param;
			}

			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();

			if (token != null) {
				connection.setRequestProperty("login-token", token);
			}

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = "";
			while(null != (line = bufferedReader.readLine())) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常: "  + e);
			e.printStackTrace();
		}
		return result;
	}
}
