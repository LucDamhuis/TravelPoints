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
import HATEOS.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tripuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "user.findByname", query = "SELECT u FROM User u WHERE u.username = :name OR u.email = :name"),
    @NamedQuery(name = "user.loginEmail", query = "SELECT u FROM User u WHERE u.email =:email AND u.password = :password"),
    @NamedQuery(name = "user.loginUsername", query = "SELECT u FROM User u WHERE u.username =:username AND u.password = : password"),
    @NamedQuery(name = "user.count", query = "SELECT COUNT(u) FROM User u"),
    @NamedQuery(name = "user.getFollowing", query = "SELECT u FROM User u WHERE :user MEMBER OF u.followingUsers ")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private String username;

    private String firstname;

    private String lastname;

    private String dob;

    private String email;

    @OneToMany
    @JsonIgnore
    private List<User> followingUsers;

    @ManyToMany
    @JsonIgnore
    private List<Trip> followingTrips;

    @ManyToMany
    @JsonIgnore
    private List<Step> followingSteps;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    private String password;

    @Transient
    private Set<Link> links = new HashSet<>();

    public User() {
    }

    public User(String username, String firstname, String lastname, String dob, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.followingUsers = new ArrayList<>();
        this.followingTrips = new ArrayList<>();
        this.followingSteps = new ArrayList<>();
        this.password = password;
        this.links = new HashSet<>();

    }

    public User(String username, String firstname, String lastname, String dob, String email, String password, Role role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.role = role;
        this.followingUsers = new ArrayList<>();
        this.followingTrips = new ArrayList<>();
        this.followingSteps = new ArrayList<>();
        this.password = password;
        this.links = new HashSet<>();
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

    public void removeFollowingUser(User user) {
        if (!this.followingUsers.contains(user)) {
            throw new IllegalArgumentException("User doenst exists");
        }
        this.followingUsers.remove(user);
    }

    public void addFollowingUser(User user) {
        if (this.followingUsers.contains(user)) {
            throw new IllegalArgumentException("User doenst exists");
        }
        this.followingUsers.add(user);
    }

    public List<Trip> getFollowingTrips() {
        return followingTrips;
    }

    public void RemoveFollowingTrip(Trip trip) {
        if (!this.followingTrips.contains(trip)) {
            throw new IllegalArgumentException("Trip doenst exists");
        }
        this.followingTrips.remove(trip);
    }

    public void addFollowingTrip(Trip trip) {
        if (this.followingTrips.contains(trip)) {
            throw new IllegalArgumentException("Trip already exists");
        }
        this.followingTrips.add(trip);
    }

    public void setFollowingTrips(List<Trip> followingTrips) {
        this.followingTrips = followingTrips;
    }

    public List<Step> getFollowingSteps() {
        return followingSteps;
    }

    public void setFollowingSteps(List<Step> followingSteps) {
        this.followingSteps = followingSteps;
    }

    public void removeFollowingStep(Step step) {
        if (!this.followingSteps.contains(step)) {
            throw new IllegalArgumentException("Step doenst exists");
        }
        this.followingSteps.remove(step);
    }

    public void addFollowingStep(Step step) {
        if (this.followingTrips.contains(step)) {
            throw new IllegalArgumentException("Step already exists");
        }
        this.followingSteps.add(step);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link(url, rel);
        links.add(link);
    }

}
