package com.romTestProj.SOAWebAppTest.controllers;


import com.romTestProj.SOAWebAppTest.models.Person;
import com.romTestProj.SOAWebAppTest.servicies.RegistrationService;
import com.romTestProj.SOAWebAppTest.utils.*;
import com.romTestProj.SOAWebAppTest.utils.exceptions.UserNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final PersonValidator personValidator;
	private final RegistrationService registrationService;
	
	@Autowired
	public AuthController(PersonValidator personValidator,
	                      RegistrationService registrationService) {
		this.personValidator = personValidator;
		this.registrationService = registrationService;
	}
	
	@GetMapping("/login")
	public String loginPage(){
		
		return "/login";
	}
	
	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> performRegistration(@RequestBody @Valid Person person,
	                                                      BindingResult bindingResult){
		//valid if there is no such person in bd with validator
		
		personValidator.validate(person, bindingResult);//check for errors
		if(bindingResult.hasErrors()){
			StringBuilder errorsMsg = new StringBuilder(); //create one error message
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError e : errors){
				errorsMsg.append(e.getField()).append(" : ")
						.append(e.getDefaultMessage())
						.append("; ");
			}
			throw new UserNotCreatedException(errorsMsg.toString()); //exception would save an error message
		}
		registrationService.registerNewUser(person);
		
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> exceptionHandler(UserNotCreatedException exception){
		PersonErrorResponse personErrorResponse = new PersonErrorResponse(
				"Error: " +
						exception.getErrorNumber() +
						" : " + exception.getMessage());
		return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
