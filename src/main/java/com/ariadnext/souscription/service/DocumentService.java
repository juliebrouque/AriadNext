package com.ariadnext.souscription.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ariadnext.souscription.model.Document;

@Service
public interface DocumentService {
	
	public String upload(HttpServletRequest request, Document document)  throws  Exception;
	
	public Document saveDocument (Document document); 
	
	public Optional<Document> getDocument (Long id);
	
	public Iterable<Document> getDocuments();
	
	public void deleteDocument (Long id);

}
