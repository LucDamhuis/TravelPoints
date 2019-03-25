package com.mycompany.travelpoint.domain;

import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-25T09:36:08")
@StaticMetamodel(Trip.class)
public class Trip_ { 

    public static volatile SingularAttribute<Trip, Double> startLat;
    public static volatile SingularAttribute<Trip, Double> endLat;
    public static volatile SingularAttribute<Trip, User> tripTaker;
    public static volatile SingularAttribute<Trip, Double> startLon;
    public static volatile ListAttribute<Trip, User> followingUsers;
    public static volatile SingularAttribute<Trip, Double> endLon;
    public static volatile SingularAttribute<Trip, String> name;
    public static volatile SingularAttribute<Trip, String> description;
    public static volatile SingularAttribute<Trip, Long> id;
    public static volatile ListAttribute<Trip, Step> steps;

}