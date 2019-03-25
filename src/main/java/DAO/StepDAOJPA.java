/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Damhuis
 */
@Stateless
public class StepDAOJPA extends Facade<Step> implements StepDAO {

    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    public StepDAOJPA() {
        super(Step.class);
    }

    public StepDAOJPA(Class<Step> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Step findByName(String name) {
        TypedQuery<Step> query = em.createNamedQuery("step.findByName", Step.class);
        query.setParameter("name", name);
        List<Step> result = query.getResultList();
        return result.get(0);
    }

    public void create(Step entity) {
        super.create(entity);
    }


    public void edit(@PathParam("id") Long id, Step entity) {
        super.edit(entity);
    }


    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }


    public Step find(@PathParam("id") Long id) {
        return super.find(id);
    }

    public List<Step> findAll() {
        return super.findAll();
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
