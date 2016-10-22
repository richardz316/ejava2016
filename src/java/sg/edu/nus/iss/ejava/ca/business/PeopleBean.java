package sg.edu.nus.iss.ejava.ca.business;


import sg.edu.nus.iss.ejava.ca.model.People;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E0015387
 */
public class PeopleBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void add(People people) {
        
    }
    
}
