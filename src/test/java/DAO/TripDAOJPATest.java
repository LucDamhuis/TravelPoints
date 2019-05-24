/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Damhuis
 */
public class TripDAOJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TripDAOJPA tripDAOJPA;
    private UserDAOJPA userDAOJPA;

    public TripDAOJPATest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tripDAOJPA = new TripDAOJPA();
        tripDAOJPA.setEm(em);
        userDAOJPA = new UserDAOJPA();
        userDAOJPA.setEm(em);
    }

    @After
    public void tearDown() {
    }

     //TODO add test methods here.
     //The methods must be annotated with annotation @Test. For example:
    
     @Test
     public void hello() {}
    @org.junit.Test
    public void testCreateAndFind() {
        tx.begin();
        List<User> users1 = new ArrayList<>();
        List<User> users2 = new ArrayList<>();
        List<User> users3 = new ArrayList<>();
        User entity = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        User entity2 = new User("Test2", "Luc2", "Damhuis2", "12-01-1993", "ldamhuis@hotmail.com", "password");
        User entity3 = new User("Test3", "Luc3", "Damhuis3", "12-01-1993", "ldamhuis@hotmail.com", "password");
        userDAOJPA.create(entity);
        userDAOJPA.create(entity2);
        userDAOJPA.create(entity3);
        tx.commit();
        tx.begin();
        users3.add(entity);
        Trip trip = new Trip("Test", "TestDesc", entity3, 0.0, 0.0, 0.0, 0.0);
        trip.addFollwingUsers(entity);
        trip.addFollwingUsers(entity2);
        tripDAOJPA.create(trip);
        tx.commit();
        Trip dbtrip = tripDAOJPA.findByName("Test");
        assertNotNull(dbtrip);
    }

    @org.junit.Test
    public void testEdit() {
        tx.begin();
        Trip trip = new Trip("Test", "Test Desc", null, 0.0, 0.0, 0.0, 0.0);
        tripDAOJPA.create(trip);
        tx.commit();
        tx.begin();
        trip.setName("Lucs trip");
        tripDAOJPA.edit(trip);
        tx.commit();
        Trip dbtrip = tripDAOJPA.findByName("Lucs trip");
        assertNotNull(dbtrip);
    }

    @org.junit.Test
    public void testRemove() {
        tx.begin();
        Trip trip = new Trip("test", "test", null, 0.0, 0.0, 0.0, 0.0);
        tripDAOJPA.create(trip);
        tx.commit();
        tx.begin();
        tripDAOJPA.removeById(Long.valueOf(trip.getId()));
        tx.commit();
        Trip dbtrip = tripDAOJPA.findByName("test");
        assertNull(dbtrip);
    }

}
