package com.ahlquist.commio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com" })

public class VideoStreamTempleteApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamTempleteApplication.class, args);
	}

	

}
