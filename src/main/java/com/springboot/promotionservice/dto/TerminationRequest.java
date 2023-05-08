package com.springboot.promotionservice.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminationRequest {
	
	@NotNull(message = "severity shouldn't be null")
	private String severity;
	@NotNull(message = "reason shouldn't be null")
	private String reason;
	@NotNull(message = "forceTermination shouldn't be null")
	private String forceTermination;
	

}
