package com.ariadnext.souscription.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ariadnext.souscription.model.Personne;

@Controller
public interface PersonneController {
	
	@RequestMapping(value = "/createPersonne", method = RequestMethod.POST)
	@ResponseBody
	public Personne createPersonne(HttpServletRequest request, Personne personne) ;
	
	@RequestMapping(value = "/getPersonneById", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Personne> getPersonne (HttpServletRequest request, Long id);
	
	@RequestMapping(value = "/getPersonnes", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Personne> getPersonnes();
	
	@RequestMapping(value = "/deletePersonne", method = RequestMethod.GET)
	@ResponseBody
	public void deletePersonne (Long id);


}
