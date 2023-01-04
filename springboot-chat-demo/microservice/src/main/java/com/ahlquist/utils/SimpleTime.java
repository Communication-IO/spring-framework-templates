package com.ahlquist.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A Utility class containing a conversion function
 * 
 * @author douglas ahlquist
 *
 */
public class SimpleTime {

	public static String FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static String convertTimestampToString(final @NonNull Timestamp timestamp) {
		return convertTimestampToString(FORMAT, timestamp);
	}

	public static String convertTimestampToString(final @NonNull String format, final @NonNull Timestamp timestamp) {
		if (null == timestamp) {
			return "no timestamp";
		} else {
			return new SimpleDateFormat(format).format(timestamp);
		}
	}
}
