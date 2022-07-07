package com.romTestProj.SOAWebAppTest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romTestProj.SOAWebAppTest.utils.exceptions.UserNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



public class CustomAuthenticationFailureHandler
		extends SimpleUrlAuthenticationFailureHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MessageSource messages;
	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception)
			throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> data = new HashMap<>();
		data.put(
				"timestamp",
				Calendar.getInstance().getTime());
		data.put(
				"exception",
				exception.getMessage());
		
		response.getOutputStream()
				.println(objectMapper.writeValueAsString(data));
	}
	
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> exceptionHandler(InternalAuthenticationServiceException exception){
		System.out.println("ffffff");
		PersonErrorResponse personErrorResponse = new PersonErrorResponse(
				"Error: " + exception.getMessage());
		return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
