package com.ariadnext.souscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public interface FacebookController {
	
	public String feed(Model model) throws Exception;

}
