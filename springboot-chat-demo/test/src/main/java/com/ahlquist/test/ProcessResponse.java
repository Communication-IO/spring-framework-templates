package com.ahlquist.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

public class ProcessResponse {

	/**
	 * Process a HttpGet request and response, and calls process for the response
	 * 
	 * @param request
	 * @param response
	 * @param label    - used for logging
	 * @return the appropriate response as a String
	 * @throws IOException
	 */
	public static String process(final HttpGet request, final HttpResponse response, final String label)
			throws IOException {

		System.out.println("Request Method: " + request.getMethod());
		System.out.println("Request URI: " + request.getURI());
		return process(response, label);
	}

	/**
	 * Process a HttpPost request and response, and calls process for the response
	 * 
	 * @param request
	 * @param response
	 * @param label    - used for logging
	 * @return the appropriate response as a String
	 * @throws IOException
	 */
	public static String process(final HttpPost request, final HttpResponse response, final String label)
			throws IOException {

		System.out.println("Request Method: " + request.getMethod());
		System.out.println("Request URI: " + request.getURI());
		
		Header[] headers = request.getAllHeaders();
		String content = EntityUtils.toString(request.getEntity());

		System.out.println(request.toString());
		for (Header header : headers) {
		    System.out.println(header.getName() + ": " + header.getValue());
		}
		System.out.println();
		System.out.println(content);
		return process(response, label);
	}

	/**
	 * Processes only the HttpResponse
	 * 
	 * @param response
	 * @param label    - used for logging
	 * @return the appropriate response as a String
	 * @throws IOException
	 */
	public static String process(final HttpResponse response, final String label) throws IOException {

		StringBuilder sb = new StringBuilder();
		int code = response.getStatusLine().getStatusCode();
		if (code < 300) {
			System.out.println(code);
		} else {
			// for a little more notice
			System.err.println(">>>>> " + code + " <<<<<");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(label + " status: " + response.getStatusLine());
		return sb.toString();
	}
}
