package com.romTestProj.SOAWebAppTest.repositories;

import com.romTestProj.SOAWebAppTest.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
	Optional<Person> findByUsername(String name);
}
