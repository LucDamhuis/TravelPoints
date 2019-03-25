/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damhuis
 */
public class UserDAOCollTest {

    UserDAOColl instance;

    public UserDAOCollTest() {
    }

    @Before
    public void setUp() {
        instance = new UserDAOColl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        instance.create(user);
        assertEquals(instance.getAll().size(), 1);
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        instance.remove(user);
        assertEquals(instance.getAll().size(), 0);
    }

    @Test
    public void testFindbyUsername() {
        System.out.println("findbyUsername");
        String username = "LucD";
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        instance.create(user);
        User expResult = user;
        User result = instance.findbyUsername(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<User> result = instance.getAll();
        for (User u : result) {
            instance.remove(u);
        }
        User user = new User("LucD", "Luc", "Damhuis", "01-12-1993", "ldamhuis@hotmail.com", null);
        User user2 = new User("LucF", "Luc", "Fransen", "01-12-1993", "ldamhuis@hotmail.com", null);
        instance.create(user);
        instance.create(user2);
        List<User> results = instance.getAll();
        assertEquals(results.size(),2);
    }
}
