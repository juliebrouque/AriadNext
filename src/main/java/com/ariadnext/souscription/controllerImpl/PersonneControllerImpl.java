package com.ariadnext.souscription.controllerImpl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ariadnext.souscription.controller.PersonneController;
import com.ariadnext.souscription.model.Personne;
import com.ariadnext.souscription.service.PersonneService;

@Component
public class PersonneControllerImpl implements PersonneController{
	
	@Autowired
	private PersonneService personneService;


	public Personne  createPersonne(HttpServletRequest request, Personne personne) {
		return personneService.savePersonne(personne);
	}

	@Override
	public Optional<Personne> getPersonne(HttpServletRequest request, Long id) {
		return personneService.getPersonne(id);
	}

	@Override
	public Iterable<Personne> getPersonnes() {
		return personneService.getPersonnes();
	}

	@Override
	public void deletePersonne(Long id) {
		personneService.deletePersonne(id);
		
	}

}
