package com.ahlquist.test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

public class GetMessages {

	/**
	 * Get Messages 
	 * @param token
	 * @param recipient
	 * @param start
	 * @param limit
	 * @return List<Message> on Success or Error Messages
	 */
	public static String getMessages(final String token, String recipient, final Integer start, Integer limit) {
		String out = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(TestApp.HOST + "/messages");
			request.addHeader("Content-type", "application/json");
			request.addHeader("Accepts", "application/json");
			request.addHeader("token", token);
			//request.addHeader("recipient", recipient);

			//recipient = encodeValue(recipient);

			URI uri = new URIBuilder(request.getURI())
			//		.addParameter("recipient", URLEncoder.encode(recipient, StandardCharsets.UTF_8))
					.addParameter("start", start.toString())
					.addParameter("limit", limit.toString()).build();
			
			request.setURI(uri);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(response, "Get Messages");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

}
