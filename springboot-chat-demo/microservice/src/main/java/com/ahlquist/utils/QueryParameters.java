package com.ahlquist.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class QueryParameters {

	public static Map<String, String[]> getQueryParameters(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, String[]> queryParameters = new HashMap<>();
		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
			String[] parameters = queryString.split("&");
			for (String parameter : parameters) {
				String[] keyValuePair = parameter.split("=");
				String[] values = queryParameters.get(keyValuePair[0]);
				// length is one if no value is available.
				values = keyValuePair.length == 1 ? ArrayUtils.add(values, "")
						: ArrayUtils.addAll(values, keyValuePair[1].split(",")); // handles CSV separated query param
																					// values.
				queryParameters.put(keyValuePair[0], values);
			}
		}
		return queryParameters;
	}
}
