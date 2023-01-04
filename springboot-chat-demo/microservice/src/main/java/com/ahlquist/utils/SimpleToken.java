package com.ahlquist.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 
 * A utility function that creates a token, These should always be unique as part of it 
 * is the System.currentTimeMillis which is a long
 * 
 * @author douglasahlquist
 *
 */
public class SimpleToken {

	private static final String SALT_OF_SORTS = "H00v3r!sMyDoggi3";

	public static String encode(String str) {

		String temp = str + System.currentTimeMillis() + SALT_OF_SORTS;
		return encode(temp.getBytes());
	}

	private static String encode(byte[] bytes) {

		String temp = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

		List<Character> characters = new ArrayList<Character>();
		for (char c : temp.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(temp.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}

		return Base64.getUrlEncoder().withoutPadding().encodeToString(output.toString().getBytes());
	}

}
