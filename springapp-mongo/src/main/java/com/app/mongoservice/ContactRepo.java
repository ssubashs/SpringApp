package com.app.mongoservice;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.document.ContactDoc;

@RepositoryRestResource(collectionResourceRel = "mongo_contact", path = "contact")
public interface ContactRepo  extends MongoRepository<ContactDoc,String>, Serializable{	

}
