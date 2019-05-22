/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Services.UserService;
import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damhuis
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {
    @Inject
    UserService userService;
    
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    
    private String filter;
    public ArrayList<User> getUsers(){
        return (ArrayList<User>) userService.findAll();
    }
    
    public void addUser(){
        User u = new User(username, firstname, lastname, email, email, "password");
        userService.createUser(u);
    }

    public void removeStudent(User u) {
        userService.remove(u);
    }
    
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getUserName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
