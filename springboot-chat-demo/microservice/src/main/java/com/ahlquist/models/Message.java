package com.ahlquist.models;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * An Entity class for Messages
 * <code>
"messages": [
  {
	"id": 0,
	"timestamp": "2019-08-24T14:15:22Z",
	"sender": 0,
	"recipient": 0,
	"content": {
		"type": "string",
		"text": "string"
	}
  }
]
</code>
 * @author douglasahlquist
 */

@Entity(name = "Message")
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false)
	Integer id;

	// "timestamp": "2019-08-24T14:15:22Z",
	@Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp timestamp;

	// "sender": 0,
	@Column(name = "sender", nullable = false)
	@Type(type = "uuid-char")
	UUID sender;

	// "recipient": 0,
	@Column(name = "recipient", nullable = false)
	@Type(type = "uuid-char")
	UUID recipient;

	// "type": "string",
	@Column(name = "contenttype", nullable = false, length = 32)
	String contentType;
	
	// Text could be either the text message if 'plain/text' or the a URL to an S3 object
	// "text": "string"
	@Column(name = "contenttext", nullable = false, length = 512)
	String contenttext;

	public Message() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID sender) {
		this.sender = sender;
	}

	public UUID getRecipient() {
		return recipient;
	}

	public void setRecipient(UUID recipient) {
		this.recipient = recipient;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentText() {
		return contenttext;
	}

	public void setContentText(String contenttext) {
		this.contenttext = contenttext;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentType, contenttext, id, recipient, sender, timestamp);
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", timestamp=" + timestamp + ", sender=" + sender + ", recipient=" + recipient
				+ ", contentType=" + contentType + ", contenttext=" + contenttext + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(contentType, other.contentType) && Objects.equals(contenttext, other.contenttext)
				&& Objects.equals(id, other.id) && Objects.equals(recipient, other.recipient)
				&& Objects.equals(sender, other.sender) && Objects.equals(timestamp, other.timestamp);
	}

}
