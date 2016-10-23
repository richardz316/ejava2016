/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.ejava.ca.business.AppointmentBean;
import sg.edu.nus.iss.ejava.ca.business.PeopleBean;
import sg.edu.nus.iss.ejava.ca.model.Appointment;
import sg.edu.nus.iss.ejava.ca.model.People;

/**
 *
 * @author rzhao
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    @EJB private PeopleBean peopleBean;
    @EJB private AppointmentBean appointmentBean; 
    
    @Resource(mappedName = "concurrent/myThreadPool")
    private ManagedExecutorService executors;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void createPeople(MultivaluedMap<String, String> formData
        , @Suspended AsyncResponse async) {
        String name = formData.getFirst("name");
	String email = formData.getFirst("email");
        
        CreatePeopleTask cpTask = new CreatePeopleTask();
        cpTask.setPeopleData(name, email, peopleBean);
        cpTask.setAsyncResponse(async);
        
        executors.execute(cpTask);
        
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void findAppointmentById(@Context UriInfo info, @Suspended AsyncResponse async) {
        
        String email = info.getQueryParameters().getFirst("email");        
        
        FetchAppointmentTask fptTask = new FetchAppointmentTask(); 
        fptTask.setPeopleData(email, appointmentBean);
        fptTask.setAsyncResponse(async);
        
        executors.execute(fptTask);
        
        
//        Optional<List<Appointment>> appointmentList = appointmentBean.getAllAppointmentByEmail(email);
//      
//        if (appointmentList.isPresent()) {            
//            JsonArrayBuilder jsonArray = Json.createArrayBuilder(); 
//            List<Appointment> appointments = appointmentList.get(); 
//            for(Appointment appointment: appointments) {
//                jsonArray.add(appointment.toJSON());
//            }           
//            
//            return (Response.ok(jsonArray.build()).build());
//        }
//        
//        return (Response.status(Response.Status.NOT_FOUND)
//                .entity("Email not found:" + email)
//                .build());
    }
    
    
    
    
    
    
}
