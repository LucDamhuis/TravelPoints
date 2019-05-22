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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damhuis
 */
public class TripDAOCollTest {

    TripDAOColl instance;

    public TripDAOCollTest() {
    }

    @Before
    public void setUp() {
        instance = new TripDAOColl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        User user = new User("Luc", "Luc", "Damhuis", "01-12-1993", "ldam@hotmail.com", "password");
        Step step = new Step("StepName", "StepDescription", 0.0, 0.0, 0.0, 0.0);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip("TripName", "TripDescription", user, 0.0, 0.0, 0.0, 0.0);
        trip.AddStep(step);
        instance.create(trip);
        assertEquals(instance.getAllTrips().size(), 1);
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        User user = new User("Luc", "Luc", "Damhuis", "01-12-1993", "ldam@hotmail.com", "password");
        Step step = new Step("StepName", "StepDescription", 0.0, 0.0, 0.0, 0.0);
        ArrayList<Step> steps = new ArrayList<>();
        Trip trip = new Trip("TripName", "TripDescription", user, 0.0, 0.0, 0.0, 0.0);
        trip.AddStep(step);
        instance.remove(trip);
        assertEquals(instance.getAllTrips().size(), 0);
    }

    @Test
    public void testFindByName() {
        System.out.println("findByName");
        User user = new User("Luc", "Luc", "Damhuis", "01-12-1993", "ldam@hotmail.com", "password");
        Step step = new Step("StepName", "StepDescription", 0.0, 0.0, 0.0, 0.0);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip("TripName", "TripDescription", user, 0.0, 0.0, 0.0, 0.0);
        trip.AddStep(step);
        instance.create(trip);
        Trip returnTrip = instance.findByName(trip.getName());
        assertEquals(returnTrip, trip);
    }

    @Test
    public void testGetAllTrips() {
        
        System.out.println("getAllTrips");
        TripDAOColl instance = new TripDAOColl();
        User user = new User("Luc", "Luc", "Damhuis", "01-12-1993", "ldam@hotmail.com", "password");
        Step step = new Step("StepName", "StepDescription", 0.0, 0.0, 0.0, 0.0);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip("TripName", "TripDescription", user, 0.0, 0.0, 0.0, 0.0);
        trip.AddStep(step);
        User user2 = new User("LucD2", "Luc2", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com","password");
        Step step2 = new Step("teststep2", "testdesctription2", 10.0, 11.0, 12.0, 13.0);
        ArrayList<Step> steps2 = new ArrayList<>();
        Trip trip2 = new Trip("Scotland trip2", "Doing the norhtcoast 500 very exited", user, 20.0, 21.0, 22.0, 23.0);
        trip2.AddStep(step2);
        instance.create(trip);
        instance.create(trip2);
        List<Trip> expResult = new ArrayList<>();
        expResult.add(trip);
        expResult.add(trip2);
        List<Trip> result = instance.getAllTrips();
        assertEquals(expResult, result);
    }

    

}
