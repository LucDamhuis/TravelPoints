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
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip(1L, "Scotland trip", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        instance.create(trip);
        assertEquals(instance.getAllTrips().size(), 1);
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        Trip trip = new Trip(1L, "Scotland trip", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        instance.remove(trip);
        assertEquals(instance.getAllTrips().size(), 0);
    }

    @Test
    public void testFindByName() {
        System.out.println("findByName");
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip(1L, "Scotland trip", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        instance.create(trip);
        Trip returnTrip = instance.findByName(trip.getName());
        assertEquals(returnTrip, trip);
    }

    @Test
    public void testGetAllTrips() {
        System.out.println("getAllTrips");
        TripDAOColl instance = new TripDAOColl();
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip(1L, "Scotland trip", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        User user2 = new User("LucD2", "Luc2", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step2 = new Step("teststep2", "testdesctription2", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step2);
        Trip trip2 = new Trip(2L, "Scotland trip2", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        for (Trip t : instance.getAllTrips()) {
            instance.remove(t);
        }
        instance.create(trip);
        instance.create(trip2);
        List<Trip> expResult = new ArrayList<>();
        expResult.add(trip);
        expResult.add(trip2);
        List<Trip> result = instance.getAllTrips();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAllTripsOfUser() {
        System.out.println("getAllTripsOfUser");
        String username = "LucD";
        TripDAOColl instance = new TripDAOColl();
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip(1L, "Scotland trip", "Doing the norhtcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        User user2 = new User("Henk", "Luc2", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step2 = new Step("teststep2", "testdesctription2", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step2);
        Trip trip2 = new Trip(2L, "Scotland trip2", "Doing the norhtcoast 500 very exited", user2, steps2, 20.0, 21.0, 22.0, 23.0, null);
        for (Trip t : instance.getAllTrips()) {
            instance.remove(t);
        }
        instance.create(trip);
        instance.create(trip2);
        List<Trip> expResult = new ArrayList<>();
        expResult.add(trip);
        List<Trip> result = instance.getAllTripsOfUser(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetByDescription() {
        System.out.println("getByDescription");
        String description = "northcoast";
        TripDAOColl instance = new TripDAOColl();
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step);
        Trip trip = new Trip(1L, "Scotland trip", "Doing the northcoast 500 very exited", user, steps, 20.0, 21.0, 22.0, 23.0, null);
        User user2 = new User("LucD2", "Luc2", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        Step step2 = new Step("teststep2", "testdesctription2", 10.0, 11.0, 12.0, 13.0, null);
        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step2);
        Trip trip2 = new Trip(2L, "Scotland trip2", "Doing the northcoast 500 very exited", user2, steps2, 20.0, 21.0, 22.0, 23.0, null);
        for (Trip t : instance.getAllTrips()) {
            instance.remove(t);
        }
        instance.create(trip);
        instance.create(trip2);
        List<Trip> expResult = new ArrayList<>();
        expResult.add(trip);
        expResult.add(trip2);
        List<Trip> result = instance.getByDescription(description);
        assertEquals(expResult, result);
    }

}
