package com.ahlquist.test;

import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class UserLogout {
	
	public static String userLogout(final String token, final UUID uuid) {
		String out = null;
		try {
			JSONObject json = new JSONObject();
			json.put("uuid", uuid.toString());
			System.out.println("body: \n" + TestApp.beautify(json.toString()));

			StringEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(TestApp.HOST + "/logout");

			request.addHeader("Accept", "application/json");
			request.addHeader("Content-type", "application/json");
			request.addHeader("token", token.trim());
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(request, response, "User Logout");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
