package com.romTestProj.SOAWebAppTest.servicies;

import com.romTestProj.SOAWebAppTest.models.Person;
import com.romTestProj.SOAWebAppTest.repositories.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
	
	private final PersonsRepository personsRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegistrationService(PersonsRepository personsRepository, PasswordEncoder passwordEncoder) {
		this.personsRepository = personsRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public void registerNewUser(Person person){
		person.setPassword(passwordEncoder.encode(person.getPassword())); //encrypt pass
		personsRepository.save(person); //put user with encrypted pass in db
	}
}
