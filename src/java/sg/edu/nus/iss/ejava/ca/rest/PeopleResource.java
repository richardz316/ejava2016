/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava.ca.business.PeopleBean;
import sg.edu.nus.iss.ejava.ca.model.People;

/**
 *
 * @author rzhao
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    @EJB private PeopleBean peopleBean;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPeople(MultivaluedMap<String, String> formData) {
        String name = formData.getFirst("name");
	String email = formData.getFirst("email");
        
        peopleBean.add(name, email);
        
        JsonObject json = Json.createObjectBuilder()
				.add("name", name)
				.add("email", email)
				.build();
        
        return (Response.status(Response.Status.CREATED)
                .entity(json)
                .build());
    }
    
    
}
