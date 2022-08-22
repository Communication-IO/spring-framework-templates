package com.ahlquist.commio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.commio.model.User;
import com.ahlquist.commio.service.RabbitMessageProducer;

@RestController
@RequestMapping(value = "/commio-rabbitmq/")
public class RabbitMQController {

	@Autowired
	RabbitMessageProducer rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("name") String name,@RequestParam("id") String id) {
	
		User u=new User();
		u.setId(id);
		u.setName(name);
		rabbitMQSender.send(u);

		return "Message sent to the RabbitMQ Successfully";
	}

}

