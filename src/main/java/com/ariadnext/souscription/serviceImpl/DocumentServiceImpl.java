package com.ariadnext.souscription.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ariadnext.souscription.CustomProperties;
import com.ariadnext.souscription.model.Document;
import com.ariadnext.souscription.model.Personne;
import com.ariadnext.souscription.repository.DocumentRepository;
import com.ariadnext.souscription.service.ApiAriadNextService;
import com.ariadnext.souscription.service.DocumentService;

@Component
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private CustomProperties propriete;
	
	@Autowired
	private ApiAriadNextService api;
	
	@Autowired 
	private DocumentRepository documentRepository;
	
	public Optional<Document> getDocument (Long id) { 
		return documentRepository.findById(id); 
	}

	public Iterable<Document> getDocuments(){
		return documentRepository.findAll(); 
	}

	public Document saveDocument (Document document) { 
		return documentRepository.save(document); 
	}

	public void deleteDocument (Long id) { 
		documentRepository.deleteById(id); 
	}

	@Override
	public String upload(HttpServletRequest request, Document document) throws  Exception{

		String retour = null;

		// Appel de l'API AriadNext d'analyse du document
		try {
			retour = api.appelAPi(document);
		} catch (Exception e1) {
			throw new Exception("Une erreur s'est produite lors de l'analyse automatique de la pièce d'identité"); 
		}
		
		//Analyse du retour de l'API 
		JSONObject jsonObject = new JSONObject(retour);
		JSONObject identite = (JSONObject) jsonObject.get("holderDetail");

		JSONArray nom = (JSONArray) identite.get("lastName");
		JSONArray prenom = (JSONArray) identite.get("firstName");
		
		if(document.getPersonne()==null) {
			return "La personne indiquée n'exsite pas";
		}
		Personne personne = document.getPersonne();

		if(!(personne.getNom().toUpperCase().equals(nom.get(0)) && (personne.getPrenom().toUpperCase().equals(prenom.get(0))))){
			return "Les informations d'identités fournies sont icohérentes avec les informations indiquées sur la pièce d'identité fournie"; 
		}

		//Les informations sont cohérentes, on télécharge le document pour une éventuelle vérification visuelle
		// A voir si cela ne pose pas de problème métier d'une point de vue légal (RGPD?)
		String direction = propriete.getDepo();
		String uploadRootPath = direction;

		File uploadRootDir = new File(uploadRootPath);
		// Créer le répertoire si il n'existe pas.
		
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		
		MultipartFile fileData = document.getFileDatas();

		String name = fileData.getOriginalFilename();

		if (name != null && name.length() > 0) {
			try {
				
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileData.getBytes());
				stream.close();

			} catch (Exception e) {
				throw new Exception("Une erreur s'est produite lors de l'enregistrement du document"); 
			}
		}
		
		document.setCheminStockage(uploadRootDir.getAbsolutePath());

		//Enregistrement en base des informations relatives au document
		this.saveDocument(document);

		return document.getDescription();
	}

}
