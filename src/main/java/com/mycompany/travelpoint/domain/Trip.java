/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.travelpoint.domain;

import HATEOS.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Damhuis
 */
@Entity
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "trip.findByName", query = "SELECT t FROM Trip t WHERE t.name = :name"),
@NamedQuery(name = "trip.findByUser", query = "SELECT t FROM Trip t WHERE t.tripTaker.id = :id")})
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private User tripTaker;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Step> steps;

    private double startLat;

    private double startLon;

    private double endLat;

    private double endLon;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany()
    private List<User> followingUsers;

    @Transient
    private Set<Link> links = new HashSet<>();

//    @Temporal(TemporalType.DATE)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", timezone = "CET")
//    private Date startDate;
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", timezone = "CET")
//    private Date endDate;
    public Trip() {
    }

    public Trip(String name, String description, User tripTaker, double startLat, double startLon, double endLat, double endLon) {
        this.name = name;
        this.description = description;
        this.tripTaker = tripTaker;
        this.steps = new ArrayList<>();
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.followingUsers = new ArrayList<>();
        this.links = new HashSet<>();
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

    public List<Step> getSteps() {
        return steps;
    }

    public void AddStep(Step step) {
        if (this.steps.contains(step)) {
            throw new IllegalArgumentException("Step already exists");
        }
        this.steps.add(step);
    }

    public void RemoveStep(Step step) {
        if (!this.steps.contains(step)) {
            throw new IllegalArgumentException("Step doenst exists");
        }
        this.steps.remove(step);
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

    public void addFollwingUsers(User user) {
        if (this.followingUsers.contains(user)) {
            throw new IllegalArgumentException("User already follows");
        }
        this.followingUsers.add(user);
    }

    public void removeFollwingUsers(User user) {
        if (!this.followingUsers.contains(user)) {
            throw new IllegalArgumentException("User already follows");
        }
        this.followingUsers.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trip)) {
            return false;
        }
        return id != null && id.equals(((Trip) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
    
    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link(url, rel);
        links = new HashSet<>();
        links.add(link);
    }


}
