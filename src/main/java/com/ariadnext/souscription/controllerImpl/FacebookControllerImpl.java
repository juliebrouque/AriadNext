package com.ariadnext.souscription.controllerImpl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ariadnext.souscription.controller.FacebookController;
import com.ariadnext.souscription.service.FacebookService;

import net.bytebuddy.asm.Advice.OffsetMapping.Factory;

@Component
@RequestMapping("/")
public class FacebookControllerImpl implements FacebookController {

	private ConnectionRepository connectionRepository;
	
	@Autowired
	private FacebookService service;

	public FacebookControllerImpl(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}


	@GetMapping 
	public String helloFacebook(Model model) { 
		Connection<Facebook>findPrimaryConnection = connectionRepository.findPrimaryConnection(Facebook.class); 
		if(findPrimaryConnection == null) { 
			return "redirect:/connect/facebook"; 
		}
		Facebook facebook = findPrimaryConnection.getApi();
		model.addAttribute("facebookProfile",facebook.userOperations().getUserProfile()); 
		PagedList<Post> feed =facebook.feedOperations().getFeed(); model.addAttribute("feed", feed);

		return "feed"; 
	}


	@RequestMapping(value = "feed", method = RequestMethod.GET)
	public String feed(Model model) throws Exception {
		Connection<Facebook> findPrimaryConnection = connectionRepository.findPrimaryConnection(Facebook.class);

		if (findPrimaryConnection == null) {
			return "redirect:/connect/facebook";
		}

		Facebook facebook = findPrimaryConnection.getApi();
		String[] fields = { "id", "email", "first_name", "last_name", "birthday" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		
		try {
			service.saveFacebookPersonne(userProfile);
		} catch (ParseException e) {
			throw new Exception( "Une erreur s'est produite lors de l'enregistrement");
		}

		return "feed";
	}
}