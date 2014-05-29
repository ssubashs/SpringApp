package com.app.cxfrs.defs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.app.AuthoriseScope;
import com.app.domain.Person;

@Path("/customer")
@Consumes("application/json")
@Produces("application/json")
public interface ICustomerServ {
	
	
	@GET
	@Path("/")
	@AuthoriseScope(scopes={"admin"})
	public Response getAllCustomer();
	
	@GET
	@Path("/{id}/person")
	@AuthoriseScope(scopes={"admin","user","guest"})
	public Person fetchbyId(@PathParam("id")  Integer id);

}
