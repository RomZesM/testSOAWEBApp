package com.romTestProj.SOAWebAppTest.controllers;


import com.romTestProj.SOAWebAppTest.models.Person;
import com.romTestProj.SOAWebAppTest.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	
	@GetMapping("/login")
	public String loginPage(){
		
		return "login";
	}
}
