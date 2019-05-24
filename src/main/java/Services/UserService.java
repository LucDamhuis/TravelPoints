/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.JPA;
import DAO.UserDAO;
import DAO.UserDAOJPA;
import com.mycompany.travelpoint.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
@Stateless
public class UserService {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Inject
    @JPA
    private UserDAO userDAOJPA;

    public void createUser(User user) {
        userDAO.create(user);
    }

    public void edit(User user) {
        userDAOJPA.edit(user);
    }

    public void remove(User user) {
        userDAOJPA.remove(user);
    }

    public User findbyId(long id) {
        return userDAOJPA.find(id);
    }

    public List<User> findAll() {
        return userDAO.getAll();
    }



    public User findbyUsername(String username) {
        return userDAO.findbyUsername(username);
    }
}
