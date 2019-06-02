/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Damhuis
 */
public class UserDAOColl implements UserDAO{

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    
    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public User findbyUsername(String username) {
        for(User u : users){
            if(u.getUsername()==username){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }
    
    public User findbyId(Long id){
        for(User u : users){
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public void edit(User user) {
    }

    @Override
    public List<User> getFollowing(long id) {
        return null;
    }

    @Override
    public boolean loginCheckUsername(String username, String password) {
        return true;
    }

    @Override
    public boolean loginCheckEmail(String email, String password) {
        return true;
    }
    
    
    
}
