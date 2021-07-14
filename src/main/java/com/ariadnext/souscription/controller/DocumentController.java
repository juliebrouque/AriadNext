package com.ariadnext.souscription.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ariadnext.souscription.model.Document;

@Controller
public interface DocumentController {
	
	   @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	   @ResponseBody
	   public String uploadFile(HttpServletRequest request,
	         Document document)throws Exception ;
	   
	   @RequestMapping(value = "/getDocumentById", method = RequestMethod.GET)
	   @ResponseBody
	   public Optional<Document> getDocument (Long id);
	   
	   @RequestMapping(value = "/getDocuments", method = RequestMethod.GET)
	   @ResponseBody
	   public Iterable<Document> getDocuments();
	   
	   @RequestMapping(value = "/deleteDocuments", method = RequestMethod.GET)
	   @ResponseBody
	   public void deleteDocument (Long id);
}
