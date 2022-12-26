package com.ahlquist.commio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kdgregory.log4j2.aws.StatisticsMBean;

@SpringBootApplication
public class ApplicationTemplate {

    private static Logger logger = LogManager.getLogger(ApplicationTemplate.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTemplate.class, args);
    }

}
