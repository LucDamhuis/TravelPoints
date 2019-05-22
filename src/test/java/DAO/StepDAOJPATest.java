/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Step;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Damhuis
 */
public class StepDAOJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private StepDAOJPA stepDAOJPA;

    public StepDAOJPATest() {
    }

    @Before
    public void setUp() throws SQLException {

        new DatabaseCleaner(emf.createEntityManager()).clean();

        em = emf.createEntityManager();
        tx = em.getTransaction();

        stepDAOJPA = new StepDAOJPA();
        stepDAOJPA.setEm(em);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @org.junit.Test
    public void testCreateAndFind() {
        try {
            tx.begin();
            Step step = new Step("TestStep", "Step for test purposes", 0.0, 0.0, 0.0, 0.0);
            stepDAOJPA.create(step);
            tx.commit();
            List<Step> dbsteps = stepDAOJPA.findAll();
            Step dbStep = stepDAOJPA.findByName("teststep");
            assertNotNull(dbStep);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @org.junit.Test
    public void testEdit() {
        tx.begin();
        Step step = new Step("TestStep", "Step for test purposes", 0.0, 0.0, 0.0, 0.0);
        stepDAOJPA.create(step);
        tx.commit();
        tx.begin();
        step.setName("newname");
        stepDAOJPA.edit(step);
        tx.commit();
        Step dbStep = stepDAOJPA.findByName("newname");
        assertEquals(dbStep.getName(), "newname");
    }

    @org.junit.Test
    public void testRemove() {
        tx.begin();
        Step step = new Step("TestStep", "Step for test purposes", 0.0, 0.0, 0.0, 0.0);
        Step step2 = new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0);
        stepDAOJPA.create(step);
        stepDAOJPA.create(step2);
        tx.commit();
        tx.begin();
        int beforeremove = stepDAOJPA.findAll().size();
        stepDAOJPA.remove(step2);
        tx.commit();
        int afterremove = stepDAOJPA.findAll().size();
        assertEquals(beforeremove, afterremove + 1);
    }
}
