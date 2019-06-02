/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.Role;
import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

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

    @Inject
    StepService stepservice;

    public Startup() {
    }

    @PostConstruct
    private void intData() {
        User u1 = new User("Lucd", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", DigestUtils.sha256Hex("password"), new Role("admin"));
        User u2 = new User("PietJ", "Piet", "Jansen", "01-14-1987", "luc@holland2stay.com", DigestUtils.sha256Hex("testpassword"),new Role ("user"));
        List<User> users = new ArrayList<>();
        List<User> users2 = new ArrayList<>();
        List<User> users3 = new ArrayList<>()  ;
        users.add(u1);
        users2.add(u2);
        u2.setFollowingUsers(users);
        u1.setFollowingUsers(users2);
        users3.add(u2);
        users3.add(u1);
        userservice.createUser(u1);
        userservice.createUser(u2);
        Step st1 = new Step("First Step", "A step in the right direction", 0.0, 0.0, 0.0, 0.0);
        st1.setFollowingUsers(users);
        stepservice.create(st1);
        Trip t1 = new Trip("Test Trip", "A trip added for test purposes", u2, 10.0, 50.0, 60.1, 54.3);
        t1.setFollowingUsers(users3);
        List<Step> steps = new ArrayList<>();
        steps.add(st1);
        t1.setSteps(steps);
        tripservice.create(t1);

    }
}
