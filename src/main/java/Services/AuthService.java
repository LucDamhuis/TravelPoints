/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.JPA;
import DAO.UserDAO;
import com.mycompany.travelpoint.domain.User;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
public class AuthService {
    
    @Inject @JPA
    UserDAO userDao;
    
    public User login(String username,String password){
        User u = userDao.findbyUsername(username);
        if(u.getPassword()==password){
            return u;
        }
        return null;
    }
    
    
}
