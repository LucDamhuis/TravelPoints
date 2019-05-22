/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Damhuis
 */
public class UserDAOJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDAOJPA userDAOJPA;

    public UserDAOJPATest() {
    }

    @Before
    public void setUp() {

        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDAOJPA = new UserDAOJPA();
        userDAOJPA.setEm(em);
    }

    /**
     * Test of edit method, of class UserDAOJPA.
     */
    @org.junit.Test
    public void testEdit_GenericType() throws Exception {
        tx.begin();
        System.out.println("edit");
        User entity = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        userDAOJPA.create(entity);
        tx.commit();
        tx.begin();
        entity.setUsername("LDam");
        userDAOJPA.edit(entity);
        tx.commit();
        User dbuser = userDAOJPA.findbyUsername("LDam");
        assertEquals(entity, dbuser);
    }

    /**
     * Test of remove method, of class UserDAOJPA.
     */
    @org.junit.Test
    public void testRemove_GenericType() throws Exception {
        System.out.println("remove");
        tx.begin();
        User entity = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        userDAOJPA.create(entity);
        tx.commit();
        tx.begin();
        userDAOJPA.remove(entity);
        tx.commit();
        User u = userDAOJPA.findbyUsername("Test");
        assertNull(u);
    }

    /**
     * Test of findAll method, of class UserDAOJPA.
     */
    @org.junit.Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        tx.begin();
        User entity = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        User entity2 = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        userDAOJPA.create(entity);
        userDAOJPA.create(entity2);
        tx.commit();
        List<User> result = userDAOJPA.findAll();
        assertEquals(result.size(), 2);
    }

    /**
     * Test of create method, of class UserDAOJPA.
     */
    @org.junit.Test
    public void testCreate() throws Exception {
        System.out.println("create");
        tx.begin();
        User entity = new User("Test", "Luc", "Damhuis", "12-01-1993", "ldamhuis@hotmail.com", "password");
        userDAOJPA.create(entity);
        tx.commit();
        User createdUser = userDAOJPA.findbyUsername("Test");
        assertNotNull(createdUser);
    }

}
