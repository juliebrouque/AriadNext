package com.ariadnext.souscription.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ariadnext.souscription.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{

}
