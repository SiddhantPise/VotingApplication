package com.SP.voting.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourseNotFoundExecption.class)
	public ResponseEntity<ErrorResponse>handleResourseNotFoundException(ResourseNotFoundExecption ex){
		ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DuplicateResourseException.class)
	public ResponseEntity<ErrorResponse>handleDuplicateResourseException(DuplicateResourseException ex){
		ErrorResponse err = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(VoteNotAllowedException.class)
	public ResponseEntity<ErrorResponse>handleVoteNotAllowedException(VoteNotAllowedException ex){
		ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		BindingResult bresult = ex.getBindingResult();
		List<FieldError>errorlist= bresult.getFieldErrors();
	    for(FieldError error : errorlist) {
	    	errors.put(error.getField(), error.getDefaultMessage());
	    }
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse>handleGenralException(Exception ex){
		ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
