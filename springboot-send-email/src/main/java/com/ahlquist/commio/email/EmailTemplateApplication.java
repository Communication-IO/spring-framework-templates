package com.ahlquist.commio.email;

import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.email.util.EmailUtil;

@SpringBootApplication
public class EmailTemplateApplication implements CommandLineRunner {
	@Autowired
	private EmailUtil emailUtil;

	public static void main(String[] args) {
		SpringApplication.run(EmailTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			emailUtil.sendEmail();
		} catch (MessagingException | IOException e) {

			e.printStackTrace();
		}

	}
}
