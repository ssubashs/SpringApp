package com.app.mongoservice;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.document.PersonDoc;

@RepositoryRestResource(collectionResourceRel = "mongo_person", path = "people")
public interface PersonRepo  extends MongoRepository<PersonDoc,String>, Serializable{	

}
