package com.romTestProj.SOAWebAppTest.utils;


import com.romTestProj.SOAWebAppTest.models.Person;
import com.romTestProj.SOAWebAppTest.servicies.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
	private final PersonDetailService personDetailService;
	
	@Autowired
	public PersonValidator(PersonDetailService personDetailService) {
		this.personDetailService = personDetailService;
	}
	
	
	@Override
	public boolean supports(Class<?> checkedClass) {
		return Person.class.equals(checkedClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		//TODO people service for validator with Optional
		Person person = (Person) target;
		try{
			personDetailService.loadUserByUsername(person.getUsername());
		}
		catch (UsernameNotFoundException ignored){ //if exception was thrown -> its ok => there is no such user in BD
			return;
		}
		
		errors.rejectValue("username", "", "Name is unavailable, try other");
	}
}
