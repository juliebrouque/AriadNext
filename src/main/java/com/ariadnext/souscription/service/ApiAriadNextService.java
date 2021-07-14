package com.ariadnext.souscription.service;

import org.springframework.stereotype.Service;

import com.ariadnext.souscription.model.Document;

@Service
public interface ApiAriadNextService {
	
	public String appelAPi (Document document) throws Exception;

}
