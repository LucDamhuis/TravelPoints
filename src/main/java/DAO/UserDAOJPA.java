/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Damhuis
 */
@Stateless
@JPA
public class UserDAOJPA extends Facade<User> implements UserDAO {
    @EJB
    private TripDAOJPA tripDAOJPA;
    
    @EJB
    private StepDAOJPA StepDAOJPA;

    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    public UserDAOJPA() {
        super(User.class);
    }

    public UserDAOJPA(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(User entity) {
        super.create(entity);
    }

    public void edit(@PathParam("id") Long id, User entity) {
        super.edit(entity);
    }

    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    public User find(@PathParam("id") Long id) {
        return super.find(id);
    }

    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    public User findbyUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("user.findByname", User.class);
        query.setParameter("name", username);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public List<User> getAll() {
        return super.findAll();
    }
        
    
    public User addTrip(User user, Trip trip){
        List<Trip> trips = user.getTrips();
        trips.add(trip);
        em.persist(user);
        
        trip.setTripTaker(user);
        tripDAOJPA.edit(trip);
        
        return user;
    }
    
    public User addFollowingTrip(User user,Trip trip){
        user.addFollowingTrip(trip);
        em.persist(user);
        
        trip.addFollwingUsers(user);
        tripDAOJPA.edit(trip);
        return user;
    }
    
    public User addFollowingStep(User user, Step step){
        user.addFollowingStep(step);
        em.persist(user);
        
        step.addFollwingUsers(user);
        StepDAOJPA.edit(step);
        return user;        
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
