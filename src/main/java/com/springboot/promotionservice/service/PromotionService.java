package com.springboot.promotionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import com.springboot.promotionservice.dto.EmployeeDetails;
import com.springboot.promotionservice.dto.EmployeeResponse;

@Service
public class PromotionService {
	
	public List<EmployeeResponse> checkEligibility(@RequestBody List<EmployeeDetails> empDetailsList) {
		Predicate<EmployeeDetails> predicate1=emp->emp.getYearsInCurrentRole().equals("3") && emp.getGoalCompletedForCurrentYear().equals("Y");
		Predicate<EmployeeDetails> predicate2=emp->emp.getYearsInCurrentRole().equals("3") && emp.getClientAppreciationForCurrentYear().equals("Y");
		
		
		List<EmployeeDetails> list1=empDetailsList.stream().filter(predicate1.or(predicate2)).collect(Collectors.toList());  
		List<EmployeeDetails> list2=empDetailsList.stream().filter(predicate1.negate().and(predicate2.negate())).collect(Collectors.toList());  
		List<EmployeeResponse> list3=list1.stream().map(emp->EmployeeResponse.build(emp.getEmployeeId(), emp.getName(), emp.getAge(), emp.getGender(), emp.getAddress(),emp.getIsActive(), emp.getExperience(), emp.getYearsInCurrentRole(),
				emp.getGoalCompletedForCurrentYear(), emp.getClientAppreciationForCurrentYear(),"Y")).collect(Collectors.toList());
		
		List<EmployeeResponse> list4=list2.stream().map(emp->EmployeeResponse.build(emp.getEmployeeId(), emp.getName(), emp.getAge(), emp.getGender(), emp.getAddress(),emp.getIsActive(), emp.getExperience(), emp.getYearsInCurrentRole(),
				emp.getGoalCompletedForCurrentYear(), emp.getClientAppreciationForCurrentYear(),"N")).collect(Collectors.toList());
		List<EmployeeResponse> empreslist=new ArrayList<>();
		empreslist.addAll(list3);
		empreslist.addAll(list4);
		
		return	list4;
		}

}
