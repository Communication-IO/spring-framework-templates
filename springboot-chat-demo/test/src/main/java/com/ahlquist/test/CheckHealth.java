package com.ahlquist.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class CheckHealth {

	/**
	 * Checks the Applications status
	 * @return CheckHealthResponse object on Success
	 */
	public static String checkHealth() {
		String out = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(TestApp.HOST + "/check");
			request.addHeader("Content-type", "application/json");
			
			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(response, "CheckHealth");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
