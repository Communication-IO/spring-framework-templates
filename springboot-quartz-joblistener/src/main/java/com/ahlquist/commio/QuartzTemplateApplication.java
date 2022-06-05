package com.ahlquist.commio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ahlquist.commio.config.SchedulerConfig;

@Import({ SchedulerConfig.class })
@SpringBootApplication
public class QuartzTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzTemplateApplication.class, args);
	}
}