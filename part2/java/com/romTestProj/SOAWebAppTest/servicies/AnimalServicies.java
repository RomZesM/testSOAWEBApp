package com.romTestProj.SOAWebAppTest.servicies;

import com.romTestProj.SOAWebAppTest.models.Animal;
import com.romTestProj.SOAWebAppTest.repositories.AnimalRepository;
import com.romTestProj.SOAWebAppTest.utils.exceptions.AnimalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AnimalServicies {
	
	AnimalRepository animalRepository; //instead of dao
	@Autowired
	public AnimalServicies(AnimalRepository animalRepositories){
		this.animalRepository = animalRepositories;
		
	}
	
	public List<Animal> index(){
		
		return animalRepository.findAll();
	}
	
	public Animal show(int id){
		Optional<Animal> foundAnimal = animalRepository.findById(id);
		if(foundAnimal.isPresent())
			return foundAnimal.get();
		else
			throw new AnimalNotFoundException("There is no animal with this ID");
	}
	@Transactional
	public void save(Animal animal){
		animalRepository.save(animal);
	}
	
	@Transactional
	public void update(Animal updatedAnimal){
		animalRepository.save(updatedAnimal);
	}
	
	@Transactional
	public void delete(int id){
		animalRepository.deleteById(id);
	}
	
	
	
	public Animal findByAnimalName(String animalName){
		return animalRepository.findByAnimalName(animalName);
	}
	
	public Animal findByAnimalId(int animalId){
		return animalRepository.findByAnimalId(animalId);
	}
	

	
}
