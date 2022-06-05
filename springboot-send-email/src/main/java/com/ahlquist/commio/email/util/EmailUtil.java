package com.ahlquist.commio.email.util;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	private static final Logger LOG = LoggerFactory.getLogger(EmailUtil.class);

	public void sendEmail() throws MessagingException, IOException {
		LOG.info("Ready to sent an email");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("HooverDud@gmail.com");
		msg.setSubject("My First Email");
		msg.setText("Greetings from an Awse Little Doggie");
		javaMailSender.send(msg);
		LOG.info("Successfully sent an email");

	}
}