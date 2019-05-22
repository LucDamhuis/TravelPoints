package com.mycompany.travelpoint.domain;

import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-18T14:00:19")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Step> followingSteps;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile ListAttribute<User, User> followingUsers;
    public static volatile SingularAttribute<User, String> dob;
    public static volatile ListAttribute<User, Trip> trips;
    public static volatile ListAttribute<User, Trip> followingTrips;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> lastname;

}