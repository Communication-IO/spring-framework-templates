package com.ahlquist.commio.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ahlquist.commio.model.User;

@Service
public class RabbitMessageProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${commio.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${commio.rabbitmq.routingkey}")
	private String routingkey;	
	String kafkaTopic = "user_topic";
	
	public void send(User user) {
		amqpTemplate.convertAndSend(exchange, routingkey, user);
		System.out.println("Send msg = " + user);
	    
	}
}