package com.springboot.promotionservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.promotionservice.dto.TerminationRequest;
import com.springboot.promotionservice.service.Producer;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/kafka")
public class KafkaController {

	
	@Autowired
	Producer producer;
	
	@PostMapping("/producerMsg")
	public void getMessageFromClient(@Valid @RequestBody TerminationRequest request) {
		// At this moment I have pushed this message to the topic as I have some confusion about rules. I will change it 
		String message="{\"severity\":\""+request.getSeverity()+"\",\"reason\":\""+request.getReason()+"\",\"forceTermination\":\""+request.getForceTermination()+"\"}";
		producer.sendMsgToTopic(message);	
	}
}
