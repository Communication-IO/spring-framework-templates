package com.ahlquist.response;

import java.util.Objects;

public class Response<T> {

	/** Boolean indicating if request succeeded **/
	private boolean status;

	/** Message indicating error if any **/
	private String message;

	/** Additional data that is part of this response **/
	private T data;

	public Response() {
		super();
	}

	public Response(boolean status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response<T> other = (Response<T>) obj;
		return Objects.equals(data, other.data) && Objects.equals(message, other.message) && status == other.status;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, message, status);
	}

	public boolean isStatus() {
		return status;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
}
