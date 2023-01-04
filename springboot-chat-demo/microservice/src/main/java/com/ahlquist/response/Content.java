package com.ahlquist.response;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Content {

	String type;
	String text;

	public Content() {
		super();
	}

	public Content(@NonNull String type, @NonNull String text) {
		super();
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(@NonNull String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(@NonNull String text) {
		this.text = text;
	}

}
