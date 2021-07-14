package com.ariadnext.souscription.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariadnext.souscription.model.Personne;

@Service
public interface PersonneService {
	
	public Optional<Personne> getPersonne (Long id);
	
	public Iterable<Personne> getPersonnes();
	
	public void deletePersonne (Long id);
	
	public Personne savePersonne (Personne personne);

}
