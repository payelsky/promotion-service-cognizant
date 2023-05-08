package com.springboot.promotionservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.promotionservice.dto.EmployeeDetails;



@Service
public class Producer {
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	public void sendMsgToTopic(String message) {
		
		ResponseEntity<EmployeeDetails[]> responses = restTemplate.getForEntity("http://EMPLOYEE-SERVICE/employee/getDetailsWithoutPromotion", EmployeeDetails[].class );
		List<EmployeeDetails> list = Arrays.asList(responses.getBody());
		list = list.stream().map(emp-> {
		if(emp.getYearsInCurrentRole().equalsIgnoreCase("2") && emp.getGoalCompletedForCurrentYear().equalsIgnoreCase("Y")) {
			emp.setIsActive("Y");	
		}else if(emp.getYearsInCurrentRole().equalsIgnoreCase("2") && emp.getClientAppreciationForCurrentYear().equalsIgnoreCase("Y")) {
			emp.setIsActive("Y");		
		}else {
			emp.setIsActive("N");	
		}
		return emp;
		}).collect(Collectors.toList());
		kafkaTemplate.send("Payel_Topic",list.toString());	
	}

}
