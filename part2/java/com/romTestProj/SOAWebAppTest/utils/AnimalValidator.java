package com.romTestProj.SOAWebAppTest.utils;

import com.romTestProj.SOAWebAppTest.models.Animal;
import com.romTestProj.SOAWebAppTest.servicies.AnimalServicies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AnimalValidator implements Validator {
	
	private AnimalServicies animalServicies;
	@Autowired
	public AnimalValidator(AnimalServicies animalServicies){
		this.animalServicies = animalServicies;
	}
	
	@Override
	public boolean supports(Class<?> classOne) {
		
		return Animal.class.equals(classOne);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Animal animal = (Animal) target;
		System.out.println(animal);
		if(animalServicies.findByAnimalName(animal.getAnimalName()) != null)
		{
			errors.rejectValue("animalName","",
					"This name is already in use");
		}
		if(animal.getAnimalId() < 1 || animalServicies.findByAnimalId(animal.getAnimalId()) == null)
		{
			errors.rejectValue("animalId","",
					"This is no animal with this Id");
		}
		
	}
}
