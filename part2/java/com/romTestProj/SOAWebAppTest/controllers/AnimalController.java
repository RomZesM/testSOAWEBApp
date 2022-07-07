package com.romTestProj.SOAWebAppTest.controllers;

import com.romTestProj.SOAWebAppTest.models.Animal;
import com.romTestProj.SOAWebAppTest.servicies.AnimalServicies;
import com.romTestProj.SOAWebAppTest.utils.AnimalErrorResponse;
import com.romTestProj.SOAWebAppTest.utils.exceptions.AnimalNotFoundException;
import com.romTestProj.SOAWebAppTest.utils.AnimalValidator;
import com.romTestProj.SOAWebAppTest.utils.exceptions.AnimalNotUpdatedException;
import com.romTestProj.SOAWebAppTest.utils.exceptions.AnimalsNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/animals")
@RestController
public class AnimalController {
	
	private final AnimalValidator animalValidator;
	private final AnimalServicies animalServicies;

	
	@Autowired
	public AnimalController(AnimalValidator animalValidator, AnimalServicies animalServicies){
		this.animalValidator = animalValidator;
		this.animalServicies = animalServicies;
	}
	
	@GetMapping("/index")
	public List<Animal> animalIndex(){
		return animalServicies.index();
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> createNewAnimal(@RequestBody @Valid Animal animal,
	                                                BindingResult bindingResult){
		animalValidator.validate(animal, bindingResult);
		if(bindingResult.hasErrors()){
			StringBuilder errorsMsg = new StringBuilder(); //create one error message
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError e : errors){
				errorsMsg.append(e.getField()).append(" : ")
						.append(e.getDefaultMessage())
						.append("; ");
			}
				throw new AnimalsNotCreatedException(errorsMsg.toString()); //exception would save an error message
		}
		animalServicies.save(animal);
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	@GetMapping("/{animalid}")//{} -> mean that it could be any id and we get it with @PathVariable
	public Animal show(@PathVariable int animalid){
			return animalServicies.show(animalid);
	}
	
	@PatchMapping
	public ResponseEntity<HttpStatus> update(@RequestBody @Valid Animal animal,
	                     BindingResult bindingResult){
		
		animalValidator.validate(animal, bindingResult);
		if(bindingResult.hasErrors()){
			StringBuilder errorsMsg = new StringBuilder(); //create one error message
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError e : errors){
				errorsMsg.append(e.getField()).append(" : ")
						.append(e.getDefaultMessage())
						.append("; ");
			}
			throw new AnimalNotUpdatedException(errorsMsg.toString()); //exception would save an error message
		}
		
		animalServicies.update(animal);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/{animalid}")
	public ResponseEntity<HttpStatus> delete(@PathVariable int animalid){
		try{
			animalServicies.delete(animalid);
		}
		catch (EmptyResultDataAccessException e){
			throw new AnimalNotFoundException("There is no animal with this ID");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@ExceptionHandler
	private ResponseEntity<AnimalErrorResponse> exceptionHandler(AnimalsNotCreatedException exception){
		AnimalErrorResponse animalErrorResponse = new AnimalErrorResponse(
				"Error: " +
						exception.getErrorNumber() +
						" : " + exception.getMessage());
		return new ResponseEntity<>(animalErrorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	private ResponseEntity<AnimalErrorResponse> exceptionHandler(AnimalNotUpdatedException exception){
		AnimalErrorResponse animalErrorResponse = new AnimalErrorResponse(
				"Error: " +
						exception.getErrorNumber() +
						" : " + exception.getMessage());
		return new ResponseEntity<>(animalErrorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	private ResponseEntity<AnimalErrorResponse> exceptionHandler(AnimalNotFoundException exception){
		AnimalErrorResponse animalErrorResponse = new AnimalErrorResponse(
				"Error: " +
						exception.getErrorNumber() +
						" : " + exception.getMessage());
		return new ResponseEntity<>(animalErrorResponse, HttpStatus.NOT_FOUND);
	}
	
}
