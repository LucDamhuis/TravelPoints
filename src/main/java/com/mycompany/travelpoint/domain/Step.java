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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "step.findByName", query = "SELECT s FROM Step s WHERE s.name = :name")})
public class Step implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double startLat;

    private Double startLon;

    private Double endLat;

    private Double endLon;

    @ManyToMany(mappedBy = "followingSteps", fetch = FetchType.EAGER)
    private List<User> followingUsers;

    @OneToMany()
    private List<Comment> comments;

    @Transient
    private Set<Link> links;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date postDate;
    public Step(String name, String description, Double startLat, Double startLon, Double endLat, Double endLon) {
        this.name = name;
        this.description = description;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.followingUsers = new ArrayList<>();
        //this.postDate = new Date();
        this.comments = new ArrayList<>();
        this.links = new HashSet<>();

    }

    public Step() {
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

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getStartLon() {
        return startLon;
    }

    public void setStartLon(Double startLon) {
        this.startLon = startLon;
    }

    public Double getEndLat() {
        return endLat;
    }

    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }

    public Double getEndLon() {
        return endLon;
    }

    public void setEndLon(Double endLon) {
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
            throw new IllegalArgumentException("User doesn't follows");
        }
        this.followingUsers.remove(user);
    }

    public void addComment(Comment comment) {
        if (this.comments.contains(comment)) {
            throw new IllegalArgumentException("Comments already exists");
        }
        this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
        if (!this.comments.contains(comment)) {
            throw new IllegalArgumentException("Comments already removed");
        }
        this.comments.remove(comment);
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
