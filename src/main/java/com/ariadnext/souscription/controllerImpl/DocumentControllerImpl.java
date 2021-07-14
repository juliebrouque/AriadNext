package com.ariadnext.souscription.controllerImpl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ariadnext.souscription.controller.DocumentController;
import com.ariadnext.souscription.model.Document;
import com.ariadnext.souscription.model.Personne;
import com.ariadnext.souscription.service.DocumentService;
import com.ariadnext.souscription.service.PersonneService;

@Component
public class DocumentControllerImpl implements DocumentController{
		
	@Autowired
	private DocumentService service;

	public String uploadFile(HttpServletRequest request, 
			Document document) throws Exception {
		return service.upload(request, document);
	}

	@Override
	public Optional<Document> getDocument(Long id) {

		return service.getDocument(id);
	}

	@Override
	public Iterable<Document> getDocuments() {
		// TODO Auto-generated method stub
		return service.getDocuments();
	}

	@Override
	public void deleteDocument(Long id) {
		service.deleteDocument(id);
	}

}
