package com.app.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.domain.Userrole;

@RepositoryRestResource(collectionResourceRel = "mysql_role", path = "role")
public interface RoleRepo  extends CrudRepository<Userrole,Integer>, Serializable{
public final static String distinctRole = "select distinct(role.rolecd) from Userrole role";
	
		@Query(distinctRole)
		public List<String> allRoles();
	
	
}
