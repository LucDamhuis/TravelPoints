/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.travelpoint.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damhuis
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "trip.findByname", query = "SELECT t FROM Trip t WHERE t.name = :name"),
    @NamedQuery(name = "trip.findByUser", query = "SELECT t FROM Trip t WHERE t.tripTaker.username = :username")})
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User tripTaker;

    @OneToMany
    private List<Step> steps;

    private double startLat;

    private double startLon;

    private double endLat;
    
    private double endLon;
    
    private List<User> followingUsers;

    public Trip() {
    }

    public Trip(Long id, String name, String description, User tripTaker, List<Step> steps, double startLat, double startLon, double endLat, double endLon,List<User> followingUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tripTaker = tripTaker;
        this.steps = steps;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.followingUsers = followingUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getTripTaker() {
        return tripTaker;
    }

    public void setTripTaker(User tripTaker) {
        this.tripTaker = tripTaker;
    }

    @XmlTransient
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip )) return false;
        return id != null && id.equals(((Trip) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
