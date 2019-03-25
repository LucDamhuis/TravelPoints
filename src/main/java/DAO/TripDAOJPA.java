/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author Damhuis
 */
@Stateless
@JPA
public class TripDAOJPA extends Facade<Trip> implements TripDAO {

    @PersistenceContext(unitName = "persistence")
    private EntityManager em;
    UserDAOJPA userDAOJPA;


    public TripDAOJPA() {
        super(Trip.class);
        userDAOJPA = new UserDAOJPA();
    }

    public TripDAOJPA(Class<Trip> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public void create(Trip entity) {
        super.create(entity);
    }


    public void edit(@PathParam("id") Long id, Trip entity) {
        super.edit(entity);
    }

    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    @Override
    public Trip findByName(String name) {
        TypedQuery<Trip> query = em.createNamedQuery("trip.findByname", Trip.class);
        query.setParameter("name", name);
        List<Trip> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public List<Trip> getAllTrips() {
        return super.findAll();
    }

    @Override
    public List<Trip> getAllTripsOfUser(String username) {
        TypedQuery<Trip> query = em.createNamedQuery("trip.findByUser", Trip.class);
        query.setParameter("name", username);
        List<Trip> result = query.getResultList();
        return result;
    }
    
    public Trip addFollower(User user,  String tripname){
        Trip t = findByName(tripname);
        ArrayList<User> followers = (ArrayList<User>) t.getFollowingUsers();
        followers.add(user);
        t.setFollowingUsers(followers);
        em.persist(t);
        return t;
    }
    
    public Trip removeFollower(User user, String tripname){
        Trip t = findByName(tripname);
        ArrayList<User> followers = (ArrayList<User>) t.getFollowingUsers();
        followers.remove(user);
        em.persist(t);
        return t;
    }
    
        public Trip addFollower(String username,  String tripname){
        Trip t = findByName(tripname);
        ArrayList<User> followers = (ArrayList<User>) t.getFollowingUsers();
        User user = userDAOJPA.findbyUsername(username);
        followers.add(user);
        t.setFollowingUsers(followers);
        em.persist(t);
        return t;
    }
    
    public Trip removeFollower(String username, String tripname){
        Trip t = findByName(tripname);
        ArrayList<User> followers = (ArrayList<User>) t.getFollowingUsers();
        User user = userDAOJPA.findbyUsername(username);
        followers.remove(user);
        em.persist(t);
        return t;
    }
    public Trip addTripTaker(String username,Trip trip){
        User user = userDAOJPA.findbyUsername(username);
        trip.setTripTaker(user);
        em.persist(trip);
        
        ArrayList<Trip> trips = (ArrayList<Trip>) user.getTrips();
        trips.add(trip);
        user.setTrips(trips);
        em.persist(user);
        return trip;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
