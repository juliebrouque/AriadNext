package com.ariadnext.souscription.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ariadnext.souscription.model.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long>{

}
