/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Trip;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public interface TripDAO {
    
    void create(Trip trip);
    void remove (Trip trip);
    void edit(Trip trip);
    void removeById(Long id);
    Trip findByName(String name);
    List<Trip> getAllTrips();
    List<Trip> getAllTripsOfUser(String username);
    
}
