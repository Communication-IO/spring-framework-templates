package com.ahlquist.response;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class MessageRequestBinder {
	
	String message;
	MultipartFile file;
	@Override
	public int hashCode() {
		return Objects.hash(file, message);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageRequestBinder other = (MessageRequestBinder) obj;
		return Objects.equals(file, other.file) && Objects.equals(message, other.message);
	}
	public MessageRequestBinder(String message, MultipartFile file) {
		super();
		this.message = message;
		this.file = file;
	}
	public MessageRequestBinder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MessageRequestBinder [message=" + message + ", file=" + file + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
