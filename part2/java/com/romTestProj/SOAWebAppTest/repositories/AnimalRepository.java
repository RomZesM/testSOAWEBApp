package com.romTestProj.SOAWebAppTest.repositories;

import com.romTestProj.SOAWebAppTest.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
	public Animal findByAnimalName(String animalName);
	
	public Animal findByAnimalId(int animalId);
}
