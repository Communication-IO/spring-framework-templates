package com.ahlquist.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ahlquist.aws.S3BucketSingleton;
import com.ahlquist.models.Message;
import com.ahlquist.models.User;
import com.ahlquist.request.MessageRequest;
import com.ahlquist.response.Content;
import com.ahlquist.response.MessageRequestBinder;
import com.ahlquist.response.MessageResponse;
import com.ahlquist.services.MessageService;
import com.ahlquist.services.S3ChatService;
import com.ahlquist.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MediaController {

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

	public final static String PLAIN_TEXT = "plain/text";

	@Autowired
	@Qualifier(value = "UserService")
	UserService userService;

	@Autowired
	@Qualifier(value = "MessageService")
	MessageService messageService;

	/**
	 * 
	 * Send a message from one user to another. We support three types of messages
	 * text, image and video (the latter two types include metadata, please note the
	 * metadata will vary according the message type, click on "content" on the
	 * example below for more details). A real-world server would fetch the
	 * image/video URL to extract the metadata, but for our purposes it's enough to
	 * hardcode those metadata fields - we're more interested in how you decide to
	 * persist those fields.
	 * 
	 * Authorization: token HTTP Authorization Scheme: bearer REQUEST BODY SCHEMA:
	 * application/json
	 * 
	 * @param header  - The Http header
	 * @param message - The
	 * @param file    -
	 * 
	 * @return ResponseEntity<?> either ResponseEntity<String> for errors or
	 *         ResponseEntity<MessageResponse>
	 */
	@RequestMapping(value = "/media", method = RequestMethod.POST,

			headers = "content-type=multipart/*", consumes = "multipart/form-data", produces = { "application/json",
					"plain/text" })

	public ResponseEntity<?> mediaUpload(@NonNull @RequestHeader HttpHeaders headers,
			@NonNull @ModelAttribute MessageRequestBinder binder) {

		logger.debug("[media] POST ******************************************");
		MessageResponse mr = null;

		try {
			String messageAsString = binder.getMessage().trim();
			logger.debug("message: " + messageAsString);

			ObjectMapper mapper = new ObjectMapper();
			MessageRequest message = mapper.readValue(messageAsString, MessageRequest.class);

			logger.debug("MessageRequest: " + message.toString());

			MultipartFile file = binder.getFile();
			logger.debug("file: " + file.toString());

			String token = headers.get("token").get(0);
			logger.debug("[media] token: " + token);

			// empty response
			mr = new MessageResponse();

			logger.debug("sender: <" + message.getSender() + ">");
			logger.debug("recipient: <" + message.getRecipient() + ">");

			UUID sender = UUID.fromString(message.getSender());
			UUID recipient = UUID.fromString(message.getRecipient());

			String type = message.getContent().getType();
			String text = message.getContent().getText();

			InputStream stream = file.getInputStream();
			String filename = file.getOriginalFilename();

			Optional<User> oUser = userService.findByToken(token);
			if (oUser.isEmpty()) {
				logger.error("Invalid Token: " + token);
				return new ResponseEntity<String>("Invalid Token.", HttpStatus.UNAUTHORIZED);
			}
			if (!userService.exists(recipient)) {
				logger.error("Recipient does not exist" + recipient.toString());
				return new ResponseEntity<String>("Recipient does not exist", HttpStatus.BAD_REQUEST);
			}

			if (PLAIN_TEXT.equalsIgnoreCase(type)) {
				// write the data to the database and return
				Content c = new Content(type, text);
				Message m = messageService.create(sender, recipient, c);
				mr = new MessageResponse(m);
				return new ResponseEntity<MessageResponse>(mr, HttpStatus.OK);
			} else {
				logger.debug(
						"MediaType: " + type + (text.equals(PLAIN_TEXT) ? ("text: " + text) : "filename: " + text));
			}

			logger.debug("InputStream filename: " + filename);

			// Process the input stream
			S3ChatService s3s = new S3ChatService();
			List<String> list = s3s.uploadFile(stream, S3BucketSingleton.getInstance().getName(), sender, recipient,
					type);
			// String contentText = list.get(0).substring(0, list.get(0).length() - 2);
			logger.debug("new bucket key: " + list.get(0));
			Content c = new Content(type, list.get(0));
			mr = new MessageResponse(messageService.create(sender, recipient, c));
			stream.close();

		}
		catch (FileUploadException e) {
			// TODO(dahlquist) : Should eventually change both of these return types to	Response<T>'s
			logger.error(e.getMessage());
			return new ResponseEntity<String>("Not a multipart request.", HttpStatus.BAD_REQUEST);
			
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>("Internal server IO error: " + e.toString(), HttpStatus.BAD_REQUEST);
			
		} 

		return new ResponseEntity<MessageResponse>(mr, HttpStatus.OK);
	}
}