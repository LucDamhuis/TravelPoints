/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.TripDAOJPA;
import com.mycompany.travelpoint.domain.Trip;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public class TripService {

    public TripDAOJPA tripDAOJPA;

    public void create(Trip trip) {
        tripDAOJPA.create(trip);
    }

    public void edit(Trip trip) {
        tripDAOJPA.edit(trip);
    }

    public void remove(Long id) {
        tripDAOJPA.remove(id);
    }

    public Trip findByName(String name) {
        return tripDAOJPA.findByName(name);
    }

    public List<Trip> getAllTrips() {
        return tripDAOJPA.getAllTrips();
    }

    public List<Trip> getAllTripsOfUser(String username) {
        return tripDAOJPA.getAllTripsOfUser(username);
    }
}
