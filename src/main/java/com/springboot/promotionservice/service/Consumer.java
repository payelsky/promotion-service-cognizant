package com.springboot.promotionservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	
	@KafkaListener(topics ="Payel_Topic",groupId = "Payel_Group")
	public void listenToTopic(String message) {
		System.out.println("The received message is:::::::::::::"+message);
	}

}
