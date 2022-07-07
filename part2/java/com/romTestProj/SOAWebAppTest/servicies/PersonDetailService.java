package com.romTestProj.SOAWebAppTest.servicies;

import com.romTestProj.SOAWebAppTest.models.Person;
import com.romTestProj.SOAWebAppTest.repositories.PersonsRepository;
import com.romTestProj.SOAWebAppTest.security.PersonDetails;
import com.romTestProj.SOAWebAppTest.utils.exceptions.UserNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
	
	
	private HttpServletRequest request;
	private PersonsRepository personsRepository;
	private LoginAttemptService loginAttemptService;
	
	@Autowired
	public PersonDetailService(PersonsRepository personsRepository,
	                           HttpServletRequest request,
								LoginAttemptService loginAttemptService) {
		this.personsRepository = personsRepository;
		this.request = request;
		this.loginAttemptService = loginAttemptService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String ip = getClientIP(); //get ip to write it into cache
		if (loginAttemptService.isBlocked(ip)) {
			throw new UserNotCreatedException("blocked");
		}
		
		Optional<Person> person = personsRepository.findByUsername(username);
		//if null throw exception and error message for Spring Security
		if(person.isEmpty())
			throw new UsernameNotFoundException("No such user");
		//use a wrapper PersonDetail (implements UserDetails interface) for returning
		return new PersonDetails(person.get());//get Person object from the Optional
	}
	
	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null){
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
