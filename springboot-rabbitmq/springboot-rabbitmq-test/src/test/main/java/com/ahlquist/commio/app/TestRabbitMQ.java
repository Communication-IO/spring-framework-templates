package com.ahlquist.commio.app;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestRabbitMQ {

	public void main(String... params) {
		new TestRabbitMQ().testConsumerProducer();
	}

	@Test
	public void testConsumerProducer() {

		Thread consumer = new Thread() {

			public void run() {

			}
		};
		Thread producer = new Thread() {
			public void run() {

			}
		};

		consumer.run();
		producer.run();

		for (int i = 0; i < 6; i++) {

			try {
				Thread.sleep(1000);
				System.out.println("--------------------------------------------");
				System.out.println("out[" + i + "]: " + sendPost(i, "userName" + i));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			producer.join();
			consumer.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Complete...." );
		
	}

	public String sendPost(final int userId, final String userName) throws Exception {

		HttpPost post = new HttpPost("http://localhost:9091/api/v1/user");
		String json = "{\"userId\":\"" + userId + "\",\"userName\":\"" + userName + "\"}";
		StringEntity entity = new StringEntity(json);
		post.setEntity(entity);

		String out = null; 
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			out = EntityUtils.toString(response.getEntity());

			System.out.println(out);
		}
		return out;
	}

}
