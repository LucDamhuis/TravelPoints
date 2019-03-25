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
    List<User> getAll();
}
