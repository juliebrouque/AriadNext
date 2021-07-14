package com.ariadnext.souscription.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Documents")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Transient
	private MultipartFile fileDatas;
	
	@ManyToOne(targetEntity=Personne.class, fetch = FetchType.LAZY)
	  @JoinColumn(name = "personne_id") 
	private Personne personne;
	
	private String cheminStockage;
	
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile fileDatas) {
		this.fileDatas = fileDatas;
	}

	public String getCheminStockage() {
		return cheminStockage;
	}

	public void setCheminStockage(String cheminStockage) {
		this.cheminStockage = cheminStockage;
	}

	public Personne getPersonne() { 
		return personne; 
	}
	  
	 public void setPersonne(Personne personne) {
		 this.personne = personne;
	}
	 
}
