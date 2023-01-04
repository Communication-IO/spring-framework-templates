package com.ahlquist.test;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class UrlDecodeTest {

	String url = "http://127.0.0.1:8080/messages?recipient=29e40542-681b-45e6-a945-3948ac4fa4f6&start=0&limit=100";

	private String decode(String value) throws UnsupportedEncodingException {
		return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
	}

	@Test
	public void givenRequestParam_whenUTF8Scheme_thenDecodeRequestParams() {
//		try {
//			URI uri = new URI(url);
//
//			String scheme = uri.getScheme();
//			String host = uri.getHost();
//			String query = uri.getRawQuery();
//
//			String decodedQuery = url; Arrays.stream(query.split("&"))
//					.map(param -> {
//						try {
//							return param.split("=")[0] + "=" + decode(param.split("=")[1]);
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
//						return param;
//					})
//					.collect(Collectors.joining("&"));
//
//			assertEquals(url, scheme + "://" + host + "?" + decodedQuery);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
