package com.ariadnext.souscription.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ariadnext.souscription.model.Personne;
import com.ariadnext.souscription.repository.PersonneRepository;
import com.ariadnext.souscription.service.PersonneService;

@Component
public class PersonneServiceImpl implements PersonneService{

	  @Autowired private PersonneRepository personneRepository;
	  
	  public Optional<Personne> getPersonne (Long id) { 
		  return personneRepository.findById(id); 
	  }
	 
	  public Iterable<Personne> getPersonnes(){
		  return personneRepository.findAll(); 
	  }
	  
	  public Personne savePersonne (Personne personne) { 
		  return personneRepository.save(personne); 
	  }
	  
	  public void deletePersonne (Long id) { 
		  personneRepository.deleteById(id); 
	  }
	 
}
