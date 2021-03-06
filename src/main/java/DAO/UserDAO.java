/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public interface UserDAO {
    void create(User user);
    void remove (User user);
    User findbyUsername(String username);
    User find(Long id);
    void edit(User user);
    List<User> getAll();
    List<User> getFollowing(long id);

    public boolean loginCheckUsername(String username, String password);

    public boolean loginCheckEmail(String email, String password);
}
