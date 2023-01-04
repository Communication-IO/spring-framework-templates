package com.ahlquist.request;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.ahlquist.response.Content;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * "id": 0, "timestamp": "2019-08-24T14:15:22Z", "sender": 0, "recipient": 0,
 * "content": { "type": "string", "text": "string" }
 * 
 * @author douglasahlquist
 *
 */
public class MessageRequest {

	String sender;
	String recipient;
	Content content;

	public MessageRequest() {
		super();
	}

	public MessageRequest(String jsonStr) {
		super();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(jsonStr);

			this.sender = node.asText("sender");
			this.recipient = node.asText("recipient");
			JsonNode n = mapper.readTree("content");
			Content c = new Content();
			this.content = c;

//		final JSONObject json = new JSONObject(jsonStr);
//		this.sender = json.getString("sender");
//		this.recipient = json.optString("recipient");
//		Content c = new Content();
//		JSONObject cJson = json.getJSONObject("content");
//		c.setText(cJson.getString("text"));
//		c.setType(cJson.getString("type"));
//
//		this.content = c;
		} catch (Exception e) {

		}

	}

	public MessageRequest(@NonNull String sender, @NonNull String recipient, @NonNull Content content) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageRequest other = (MessageRequest) obj;
		return Objects.equals(content, other.content) && Objects.equals(recipient, other.recipient)
				&& Objects.equals(sender, other.sender);
	}

	public Content getContent() {
		return content;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getSender() {
		return sender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, recipient, sender);
	}

	public void setContent(@NonNull Content content) {
		this.content = content;
	}

	public void setRecipient(@NonNull String recipient) {
		this.recipient = recipient;
	}

	public void setSender(@NonNull String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "MessageRequest [sender=" + sender + ", recipient=" + recipient + ", content=" + content + "]";
	}

}
