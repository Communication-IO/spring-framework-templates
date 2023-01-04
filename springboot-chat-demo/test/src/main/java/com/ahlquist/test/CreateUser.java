package com.ahlquist.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class CreateUser {

	/**
	 * Creates a User 
	 * @param username
	 * @param password
	 * @return UserCreationResponse on Success or Error Messages
	 */
	public static String createUser(final String username, final String password) {

		String out = null;
		try {
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("password", password);
			System.out.println("body: \n" + TestApp.beautify(json.toString()));

			StringEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_FORM_URLENCODED);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(TestApp.HOST + "/user");
			request.addHeader("Accept", "application/json");
			request.addHeader("Content-type", "application/json");
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(request, response, "Create User");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

}
