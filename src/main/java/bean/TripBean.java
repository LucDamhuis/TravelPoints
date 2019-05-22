/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Services.TripService;
import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damhuis
 */
@Named(value = "tripBean")
@RequestScoped
public class TripBean {
    @Inject
    TripService tripService;
    
    private String name;
    private String description;
    
    private String filter;
    public ArrayList<Trip> getUsers(){
        return (ArrayList<Trip>) tripService.getAllTrips();
    }
    
    public void addTrip(User u){
        Trip t = new Trip(name, description, u, 0.0, 0.0, 0.0, 0.0);
        tripService.create(t);
    }

    public void removeStudent(Trip t) {
        tripService.remove(t.getId());
    }
    
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
