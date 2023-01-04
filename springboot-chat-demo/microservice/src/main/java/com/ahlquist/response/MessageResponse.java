package com.ahlquist.response;

import java.sql.Timestamp;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.ahlquist.models.Message;
import com.ahlquist.utils.SimpleTime;

/**
 * 
 * "id": 0, "timestamp": "2019-08-24T14:15:22Z", "sender": 0, "recipient": 0,
 * "content": { "type": "string", "text": "string" }
 * 
 * @author douglasahlquist
 *
 */
public class MessageResponse {

	Integer id;
	String timestamp;
	UUID sender;
	UUID recipient;
	Content content;

	public MessageResponse() {
		super();
	}
	

	public MessageResponse(@NonNull Message msg) {
		this.id = msg.getId();
		this.timestamp = SimpleTime.convertTimestampToString(msg.getTimestamp());
		this.sender = msg.getSender();
		this.recipient = msg.getSender();
		this.content = new Content(msg.getContentType(), msg.getContentText());
	}

	public MessageResponse(@NonNull Integer id, @NonNull Timestamp timestamp, @NonNull UUID sender,
			@NonNull UUID recipient, @NonNull Content content) {
		super();
		this.id = id;
		this.timestamp = SimpleTime.convertTimestampToString(timestamp);
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(@NonNull Integer id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(@NonNull String timestamp) {

		this.timestamp = timestamp;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(@NonNull UUID sender) {
		this.sender = sender;
	}

	public UUID getRecipient() {
		return recipient;
	}

	public void setRecipient(@NonNull UUID recipient) {
		this.recipient = recipient;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(@NonNull Content content) {
		this.content = content;
	}

}
