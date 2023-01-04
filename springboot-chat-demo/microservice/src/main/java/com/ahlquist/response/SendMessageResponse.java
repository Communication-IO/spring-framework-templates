package com.ahlquist.response;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SendMessageResponse {

	// "2019-08-24T14:15:22Z"
	public static String FORMAT = "2019-08-24T14:15:22Z";

	Integer id;
	String timestamp;

	public SendMessageResponse() {
		super();
	}

	public SendMessageResponse(@NonNull Integer id, @NonNull String timestamp) {
		super();
		this.id = id;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SendMessageResponse [id=" + id + ", timestamp=" + timestamp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SendMessageResponse other = (SendMessageResponse) obj;
		return Objects.equals(id, other.id) && Objects.equals(timestamp, other.timestamp);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
