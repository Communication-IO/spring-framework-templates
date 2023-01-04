package com.ahlquist.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class GetSingleMessageUrl {
	
	/**
	 * 
	 * @param token
	 * @return Message on Success or Error
	 */
	public static String getUrl(final String token) {
		String out = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(TestApp.HOST + "/url");
			request.addHeader("Content-type", "application/json");

			request.addHeader("Accepts", "application/json");
			request.addHeader("token", token);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(response, "Get Single Message");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}


}
