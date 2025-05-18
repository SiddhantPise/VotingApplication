package com.SP.voting.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	public ErrorResponse(int value, String message) {
		// TODO Auto-generated constructor stub
	}
	private int statusCode;
	private String messagestring;
	
}
