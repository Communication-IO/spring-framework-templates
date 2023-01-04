package com.ahlquist.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class SendMessageAsText {
	/**
	 * Sends one Message as Text only
	 * @param token
	 * @param sender
	 * @param recipient
	 * @return SendMessageResponse as String or Error
	 */
	public static String sentMessageAsText(final String token, final String sender, final String recipient) {
		String out = "";
		try {

			JSONObject json = new JSONObject();
			json.put("sender", sender);
			json.put("recipient", recipient);
			JSONObject content = new JSONObject();
			content.put("type", "plain/text");
			content.put("text", System.currentTimeMillis() + " Lot's and Lot's and Lot's of fun and crazy text");
			json.put("content", content);
			System.out.println("body: \n" + TestApp.beautify(json.toString()));
			
			StringEntity entity = new StringEntity(json.toString().trim(), ContentType.APPLICATION_JSON);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(TestApp.HOST + "/text");
			request.addHeader("Accept", "application/json");
			request.addHeader("Content-type", "application/json");
			request.addHeader("token", token.trim());
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(request, response, "Sent Message (AsText)");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

}
