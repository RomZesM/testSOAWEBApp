package com.romTestProj.SOAWebAppTest.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Animals")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "animal_id")
	private int animalId;
	
	@Column(name = "animal_kind")
	private String animalKind;
	
	@Column(name = "animal_name")
	@Size(min = 2, max = 100, message = "Name need to be between 2 - 100 characters")
	private String animalName;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "animal_gender")
	private String animalGender;
	
	public Animal(){
	
	
	}
	
	public int getAnimalId() {
		return animalId;
	}
	
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	
	public String getAnimalKind() {
		return animalKind;
	}
	
	public void setAnimalKind(String animalKind) {
		this.animalKind = animalKind;
	}
	
	public String getAnimalName() {
		return animalName;
	}
	
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getAnimalGender() {
		return animalGender;
	}
	
	public void setAnimalGender(String animalGender) {
		this.animalGender = animalGender;
	}
	
	@Override
	public String toString() {
		return "Animal{" +
				"animalId=" + animalId +
				", animalKind='" + animalKind + '\'' +
				", animalName='" + animalName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", animalGender='" + animalGender + '\'' +
				'}';
	}
	
	//TODO make equal and hash
}
