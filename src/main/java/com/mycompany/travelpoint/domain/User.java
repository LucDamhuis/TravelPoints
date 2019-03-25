/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.travelpoint.domain;

/**
 *
 * @author Damhuis
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tripuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "user.findByname", query = "SELECT u FROM User u WHERE u.username = :name"),
    @NamedQuery(name = "user.count", query = "SELECT COUNT(u) FROM User u")})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    private String firstname;
    
    private String lastname;
    
    private String dob;
    
    private String email;
    
    @ManyToMany
    private List<User> followingUsers;
    
    @OneToMany(
        mappedBy = "tripTaker",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Trip> trips;

    public User() {
    }

    public User(String username, String firstname, String lastname, String dob, String email ,List<User> followingUsers) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.followingUsers = followingUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    
    public void addTrip(Trip t){
        trips.add(t);
        t.setTripTaker(this);
    }
    
    public void removeTrip(Trip t){
        trips.remove(t);
        t.setTripTaker(null);
    }
    
}
