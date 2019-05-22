package com.mycompany.travelpoint.domain;

import com.mycompany.travelpoint.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-18T14:00:19")
@StaticMetamodel(Step.class)
public class Step_ { 

    public static volatile SingularAttribute<Step, Double> startLat;
    public static volatile SingularAttribute<Step, Double> endLat;
    public static volatile SingularAttribute<Step, Double> startLon;
    public static volatile ListAttribute<Step, User> followingUsers;
    public static volatile SingularAttribute<Step, Double> endLon;
    public static volatile SingularAttribute<Step, String> name;
    public static volatile SingularAttribute<Step, String> description;
    public static volatile SingularAttribute<Step, Long> id;

}