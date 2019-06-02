/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Trip;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Damhuis
 */
public class TripDAOColl implements TripDAO {

    CopyOnWriteArrayList<Trip> trips = new CopyOnWriteArrayList<>();
    
    @Override
    public void create(Trip trip) {
        trips.add(trip);
    }

    @Override
    public void remove(Trip trip) {
        trips.remove(trip);
    }

    @Override
    public Trip findByName(String name) {
        for (Trip t : trips) {
            if(t.getName().equals(name)){
                return t;
            }            
        }
        return null;
    }

    @Override
    public List<Trip> getAllTrips() {
        return new ArrayList<>(trips);
    }

    @Override
    public List<Trip> getAllTripsOfUser(Long id) {
        ArrayList<Trip> returnTrips = new ArrayList<>();
        for (Trip t : trips) {
            if(t.getTripTaker().getId().equals(id)){
                returnTrips.add(t);
            }
        }
        return returnTrips;
    }
    
    public List<Trip> getByDescription(String description) {
        ArrayList<Trip> returnTrips = new ArrayList<>();
        for (Trip t : trips) {
            if(t.getDescription().contains(description)){
                returnTrips.add(t);
            }            
        }
        return returnTrips;
    }

    @Override
    public void edit(Trip trip) {
    }

    @Override
    public void removeById(Long id) {
    }

    @Override
    public Trip find(Long id) {
        return null;
    }
    
}
