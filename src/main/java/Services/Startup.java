/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
@Singleton
@javax.ejb.Startup
public class Startup {
    
    @Inject
    UserService userservice;
    
    @Inject
    TripService tripservice;
    
    public Startup() {
    }
     
    @PostConstruct
    private void intData(){
        User u1 = new User("Lucd", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", "password");
        User u2 = new User("PietJ", "Piet", "Jansen", "01-14-1987", "luc@holland2stay.com", "testpassword");
        userservice.createUser(u1);
        userservice.createUser(u2);
        Trip t1 = new Trip("Test Trip", "A trip added for test purposes", u2, 10.0, 50.0, 60.1, 54.3);
        tripservice.create(t1);
        
    }
}
