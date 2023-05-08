package com.springboot.promotionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.promotionservice.dto.EmployeeDetails;
import com.springboot.promotionservice.dto.EmployeeResponse;
import com.springboot.promotionservice.service.PromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
	
	
	@Autowired
	private PromotionService promotionService;
	
	@PostMapping("/checkEligibility")
	public List<EmployeeResponse> checkEligibility(@RequestBody List<EmployeeDetails> hcm) {
	return	promotionService.checkEligibility(hcm);
	}

}
