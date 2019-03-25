/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Trip;
import java.sql.SQLException;
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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist");
    private EntityManager em;
    private EntityTransaction tx;
    private TripDAOJPA tripDAOJPA;

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
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @org.junit.Test
    public void testCreateAndFind() {
        Trip trip = new Trip(Long.valueOf(1), "test", "testdescription", null, null, 15.0, 16.0, 17.0, 19.0, null);
        tripDAOJPA.create(trip);
        Trip dbtrip = tripDAOJPA.findByName("test");
        assertNotNull(dbtrip);

    }

    @org.junit.Test
    public void testEdit() {
        Trip trip = new Trip(Long.valueOf(1), "test", "testdescription", null, null, 15.0, 16.0, 17.0, 19.0, null);
        tripDAOJPA.create(trip);
        trip.setName("Lucs trip");
        tripDAOJPA.edit(trip);
        Trip dbtrip = tripDAOJPA.findByName("Lucs trip");
        assertNotNull(dbtrip);
    }

    @org.junit.Test
    public void testRemove() {
        Trip trip = new Trip(Long.valueOf(1), "test", "testdescription", null, null, 15.0, 16.0, 17.0, 19.0, null);
        tripDAOJPA.create(trip);
        tripDAOJPA.remove(Long.valueOf(1));
        Trip dbtrip = tripDAOJPA.findByName("test");
        assertNull(dbtrip);
    }

}
