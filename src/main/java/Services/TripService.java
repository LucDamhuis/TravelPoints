/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.JPA;
import DAO.TripDAO;
import DAO.TripDAOJPA;
import com.mycompany.travelpoint.domain.Trip;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
@Stateless
public class TripService {

    @Inject
    @JPA
    public TripDAO tripDAOJPA;

    public void create(Trip trip) {
        tripDAOJPA.create(trip);
    }

    public void edit(Trip trip) {
        tripDAOJPA.edit(trip);
    }

    public void remove(Long id) {
        tripDAOJPA.removeById(id);
    }

    public Trip findByName(String name) {
        return tripDAOJPA.findByName(name);
    }

    public List<Trip> getAllTrips() {
        return tripDAOJPA.getAllTrips();
    }

    public List<Trip> getAllTripsOfUser(Long id) {
        return tripDAOJPA.getAllTripsOfUser(id);
    }
    public Trip findById(Long id){
        return tripDAOJPA.find(id);
    }
    
}
