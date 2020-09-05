package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControler {
	   @ExceptionHandler(value = NotFoundException.class)
	   public ResponseEntity<Object> exception(NotFoundException exception) {
	      return new ResponseEntity<>(" not found", HttpStatus.NOT_FOUND);
	   }
}
