package sg.edu.nus.iss.ejava.ca.business;


import sg.edu.nus.iss.ejava.ca.model.Appointment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E0015387
 */
public class AppointmentBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Appointment> getAllAppointmentByPid(String pid) {
        TypedQuery<Appointment> query = em.createNamedQuery(
				"Appointment.findByPid", Appointment.class);
        query.setParameter("pid", pid);
        return query.getResultList();
    }
    
}
