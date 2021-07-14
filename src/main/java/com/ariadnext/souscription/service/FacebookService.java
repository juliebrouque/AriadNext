package com.ariadnext.souscription.service;

import java.text.ParseException;

import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public interface FacebookService {
	
	public void saveFacebookPersonne (User userProfil) throws ParseException;
}
