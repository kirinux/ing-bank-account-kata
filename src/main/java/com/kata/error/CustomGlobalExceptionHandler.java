package com.kata.error;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
//	@ResponseBody
//	@ExceptionHandler(ObjectNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	String objectNotFoundHandler(ObjectNotFoundException ex) {
//		return ex.getMessage();
//	}
	
	
	 @ExceptionHandler(ObjectNotFoundException.class)
	 public void springHandleNotFound(HttpServletResponse response) throws IOException {
	        response.sendError(HttpStatus.NOT_FOUND.value());
	    }
	 
	 @ExceptionHandler(IllegalTransException.class)
	 public void springIlegalTransaction(HttpServletResponse response) throws IOException {
	       response.sendError(HttpStatus.BAD_REQUEST.value());
	 }
	

}
