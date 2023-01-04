package com.ahlquist.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ahlquist.exception.BadRequestException;
import com.ahlquist.models.Message;
import com.ahlquist.models.User;
import com.ahlquist.request.MessageRequest;
import com.ahlquist.response.Content;
import com.ahlquist.response.MessageResponse;
import com.ahlquist.services.MessageService;
import com.ahlquist.services.UserService;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	@Qualifier(value = "MessageService")
	private MessageService messageService;

	@Autowired
	@Qualifier(value = "UserService")
	private UserService userService;

	@RequestMapping(value = "/text", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> text(@RequestHeader Map<String, String> headers,
			@NonNull @RequestBody MessageRequest messageRequest) throws BadRequestException {
		
		try {
			logger.debug("[text] POST ******************************************");
			@NonNull
			String token = headers.get("token");
			logger.debug("Token found: " + token);
			if (token == null) {
				String msg = "token not found in header";
				ResponseEntity<String> ro = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
				return ro;
			}
			// validate token, get user by token
			Optional<User> oUser = userService.findByToken(token);
			if (oUser.isEmpty()) {
				String msg = "Cannot create message for invalid User token " + token;
				logger.error(msg);
				ResponseEntity<String> ro = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
				return ro;
			}

			UUID recipient = UUID.fromString(messageRequest.getRecipient());
			UUID send = UUID.fromString(messageRequest.getSender());
			Content content = messageRequest.getContent();

			logger.debug("content: " + content.toString());

			Message message = messageService.create(send, recipient, content);
			logger.debug("Message: " + message);

			MessageResponse mr = new MessageResponse(message);
			ResponseEntity<MessageResponse> ro = new ResponseEntity<>(mr, HttpStatus.OK);
			return ro;
		} 
		catch (Exception e) {
			String msg = e.getMessage();
			logger.error(msg);
			ResponseEntity<String> ro = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			return ro;
		}
	}

	/**
	 * Get Messages Fetch all existing messages to a given recipient, within a range
	 * of message IDs.
	 * 
	 * AUTHORIZATIONS: token
	 * 
	 * QUERY PARAMETERS recipient required integer <int64> User ID of recipient.
	 * 
	 * start required integer Starting message ID. Messages will be returned in
	 * increasing order of message ID, starting from this value (or the next lowest
	 * value stored in the database).
	 * 
	 * limit integer Default: 100 Limit the response to this many messages.
	 * 
	 * Header Value: Content-Type: "application/json"
	 * 
	 * @param recipient - UUID, the User ID of the recipient.
	 * @param start     - Integer, Starting message ID. Messages will be returned in
	 *                  increasing order of message ID, starting from this value (or
	 *                  the next lowest value stored in the database).
	 * @param limit     - Integer, Default: 100, Limit the response to this many
	 *                  messages.
	 *
	 * @return The List of MessageResponse objects
	 */

	@RequestMapping(value = "/messages", method = RequestMethod.GET, consumes = { "application/json",
			"plain/text" }, produces = { "application/json", "plain/text" })
	public @ResponseBody ResponseEntity<?> getMessages(@RequestHeader Map<String, String> headers,
		//DKA	@RequestParam(required = true, name = "recipient") String recipient,
			@RequestParam(required = true, name = "start") String start,
			@RequestParam(required = false, name = "limit") String limit) {
		logger.debug("[messages] GET ******************************************");
		try {
			headers.forEach((key, value) -> {
				logger.debug("Header Name: " + key + " Header Value: " + value);
			});
			Integer startInt = 0;
			Integer limitInt = 0;

			//String uuidString = URLDecoder.decode(recipient,
			//		StandardCharsets.UTF_8);
			//@NonNull 
			// uuidString = headers.get("recipient");
			//@NonNull
			//UUID recipientUuid = UUID.fromString(uuidString);
			@NonNull
			String token = headers.get("token");

			if (token == null) {
				String msg = "token " + token + " does not match User";
				logger.error(msg);
				ResponseEntity<String> ro = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
				return ro;
			}

			if (start != null) {
				startInt = Integer.valueOf(start);
			}
			if (limit != null) {
				limitInt = Integer.valueOf(100);
			} else {
				limitInt = Integer.valueOf(limit);
			}

			Optional<User> oRecipientUser = userService.findByToken(token);
			logger.debug("Optional<User>: " + oRecipientUser);

			User user = null;
			if (oRecipientUser != null) {
				user = oRecipientUser.get();
			} else {
				logger.error("oRecipientUser==null");
				ResponseEntity<String> ro = new ResponseEntity<>("RecipientUser==null", HttpStatus.NOT_FOUND);
				return ro;
			}
			logger.debug("user: " + user.toString());
			List<Message> mList = 
					messageService.findRecipientMesssageListByUserToken(user.getToken(), startInt, limitInt);
			List<MessageResponse> mrList = new ArrayList<>();

			for (Message m : mList) {
				mrList.add(new MessageResponse(m));
			}
			ResponseEntity<List<MessageResponse>> ro = new ResponseEntity<>(mrList, HttpStatus.OK);
			return ro;

		}
		catch (Exception e) {
			logger.error(e.getMessage());
			ResponseEntity<String> ro = new ResponseEntity<>(e.getMessage(),
					HttpStatus.BAD_REQUEST);
			return ro;
		}
	}
}
