package com.ariadnext.souscription.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

import com.ariadnext.souscription.model.Personne;
import com.ariadnext.souscription.service.FacebookService;
import com.ariadnext.souscription.service.PersonneService;

@Component
public class FacebookServiceImpl implements FacebookService{
	
	@Autowired
	private PersonneService personneService;
	
	public void saveFacebookPersonne (User userProfil) throws ParseException {
		Personne personne = new Personne();
		
		personne.setNom(userProfil.getLastName());
		personne.setPrenom(userProfil.getFirstName());
		if(userProfil.getBirthday()!=null) {
			Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(userProfil.getBirthday());
			personne.setDateDeNaissance(birthday);
		}

		personneService.savePersonne(personne);
	}
	
	

}
