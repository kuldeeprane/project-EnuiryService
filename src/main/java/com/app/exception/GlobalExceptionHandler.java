package com.app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = EnquiryNotFoundException.class)
	public ResponseEntity<String> EnquiryNotFoundException(EnquiryNotFoundException ef){
		
		return new ResponseEntity<String>(ef.getMessage(),HttpStatus.NOT_FOUND);
	}


}
