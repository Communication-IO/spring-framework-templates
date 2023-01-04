package com.ahlquist.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

public class SendMessageAsMedia {
	/**
	 * Test class for sending image media. For now videos are not addressed
	 * 
	 * @param token     - the generated
	 * @param sender    - UUID of the sender
	 * @param recipient - UUID of the recipient
	 * @param type      - the mime type
	 * @param text      - the filename of the media
	 * @return SendMessage on success, or some error condition
	 */
	public static String pushMedia(final String token, final String sender, final String recipient, final String type,
			final String filepath) {
		String out = null;

		File file = new File(filepath);
		Long length = file.length();
		System.out.println("Content-Length: " + length);

		try {
			JSONObject json = new JSONObject();
			json.put("sender", sender);
			json.put("recipient", recipient);
			JSONObject content = new JSONObject();
			content.put("type", type);
			content.put("text", file.getName());
			json.put("content", content);
			System.out.println("body: \n" + TestApp.beautify(json.toString()));

			HttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new ContentLengthHeaderRemover())
					.build();
			HttpPost request = new HttpPost(TestApp.HOST + "/media");
			request.addHeader("Accept", "application/json");
			//request.addHeader("Content-type", "multipart/form-data"); //; boundary=SomethingWonderfulAndBrillant");
			request.addHeader("token", token);
			// request.addHeader("Content-Length",length.toString());

			HttpEntity entity = MultipartEntityBuilder.create()
					.setCharset(StandardCharsets.UTF_8)
					.addTextBody("message", json.toString(), ContentType.TEXT_PLAIN)
					.addBinaryBody("file", file, ContentType.IMAGE_PNG, file.getName()).build();

			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			out = ProcessResponse.process(request, response, "Send Message (as image)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * The type SendMessageAsMedia.ContentLengthHeaderRemover must implement the
	 * inherited abstract method HttpRequestInterceptor.process(HttpRequest,
	 * HttpContext)
	 * 
	 * @author douglasahlquist
	 */
	private static class ContentLengthHeaderRemover implements HttpRequestInterceptor {

		@Override
		public void process(org.apache.http.HttpRequest request, HttpContext context)
				throws HttpException, IOException {

			// Fighting org.apache.http.protocol.RequestContent's
			// ProtocolException("Content-Length header already present");
			request.removeHeaders(HTTP.CONTENT_LEN);

		}
	}
}
