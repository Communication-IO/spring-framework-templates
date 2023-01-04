package com.ahlquist.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ahlquist.models.Message;
import com.ahlquist.repositories.MessageRepository;
import com.ahlquist.repositories.UserRepository;
import com.ahlquist.response.Content;

@Component(value = "MessageService")
public class MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	// UserRepository is here to enhance the call to determine if the recipient
	// (user) exists, the plan is to throw an
	// exception if the user does not exist.
	@Autowired
	@Qualifier(value = "UserRepository")
	UserRepository userRepository;

	@Autowired
	@Qualifier(value = "MessageRepository")
	MessageRepository messageRepository;

	public Message create(@NonNull UUID sender, @NonNull UUID recipient, @NonNull Content content) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return create(sender, recipient, timestamp, content);
	}

	public Message create(@NonNull UUID sender, @NonNull UUID recipient, @NonNull Timestamp timestamp,
			@NonNull Content content) {

		Message msg = new Message();
		msg.setSender(sender);
		msg.setTimestamp(timestamp);
		msg.setRecipient(recipient);
		msg.setContentText(content.getText());
		msg.setContentType(content.getType());
		logger.debug("before message.save() m: " + msg.toString());

		Message m = messageRepository.save(msg);
		logger.debug("after message.save() m: " + m.toString());
		return m;
	}

	public List<Message> findMesssageList(@NonNull UUID recipient, @NonNull Integer start, @NonNull Integer limit) {
		return messageRepository.findRecipientMesssageList(recipient, start, limit);
	}

	public List<Message> findRecipientMesssageListByUserToken(@NonNull String token, @NonNull Integer start,
			@Nullable Integer limit) {
		if (limit == null || limit < 1) {
			limit.valueOf(100);
		}
		// find the user from the current token
		// cannot get the recipient code to work
		// I see the error <Cannot convert string '\xD9\xC0\x9C\x1EcX...' from binary to
		// utf8mb4>
		// using this alternative get by token
//		Optional<User> oUser = userRepository.findByToken(token);
//		if(oUser.isEmpty()) {
//			return new ArrayList<Message>();
//		}
//		@NonNull User user = oUser.get();
//		@NonNull UUID recipientUUID = user.getUuid();

		// return messageRepository.findRecipientMesssageList(recipientUUID, start,
		// limit);
		return messageRepository.findRecipientMesssageListByUserToken(token, start, limit);
	}

}
