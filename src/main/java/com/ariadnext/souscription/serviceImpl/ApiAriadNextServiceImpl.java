package com.ariadnext.souscription.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ariadnext.souscription.CustomProperties;
import com.ariadnext.souscription.model.Document;
import com.ariadnext.souscription.service.ApiAriadNextService;


@Component
public class ApiAriadNextServiceImpl implements ApiAriadNextService{
	
	@Autowired
	private CustomProperties propriete;
	
	public String appelAPi (Document document) throws Exception {
		
	    //Construction du body JSON
	    JSONObject json = new JSONObject();
	    
	    String encodedString = Base64.getEncoder().encodeToString(document.getFileDatas().getBytes());

	    json.put("frontImage", encodedString);
	    json.put("rectoImageCropped", "true");
	    json.put("signatureImageCropped", "true");
	    json.put("faceImageCropped", "true");
	    
	    URL url = new URL(propriete.getApi());

	    //Ouverture de la connection
	    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
	    
	    connection.setRequestMethod("POST");
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    String encoded = Base64.getEncoder().encodeToString((propriete.getUsername()+":"+propriete.getPassword()).getBytes(StandardCharsets.UTF_8));  //Java 8
	    connection.setRequestProperty("Authorization", "Basic "+encoded);
	    
	    connection.setRequestProperty("Content-Type", "application/json");

	    OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	    wr.write(json.toString());
	    wr.flush();
	    wr.close();
	    connection.getOutputStream();
	    
	    int HttpResult = connection.getResponseCode(); 
	    
	    if (HttpResult == HttpURLConnection.HTTP_OK) {
	    	
	    	//Retour de connection OK, on récupère le résultat
	    	InputStream inputStream = connection.getInputStream();
	    	final StringBuilder out = new StringBuilder(); 
	        final byte[] buffer = new byte[1024]; 
	        try {
	            for (int ctr; (ctr = inputStream.read(buffer)) != -1;) {
	                out.append(new String(buffer, 0, ctr));
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Impossible de convertir le stream en string", e);
	        }
	    	
	    	return out.toString(); 
	    	
	    } else {
	    	return connection.getResponseMessage();  
	    }  
	}
}
