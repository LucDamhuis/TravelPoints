/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Damhuis
 */
@Stateless
@JPA
public class CommentDAOJpa extends Facade<Comment> implements CommentDAO {

    @Inject
    @JPA
    StepDAO stepDAOJPA;

    public CommentDAOJpa() {
         super(Comment.class);
    }
    public CommentDAOJpa(Class<Comment> entityClass) {
        super(entityClass);
    }

    
    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Comment comment) {
        super.create(comment);
        Step s = stepDAOJPA.find(comment.getStepId());
        stepDAOJPA.addComment(s, comment);
    }

    @Override
    public void remove(Comment comment) {
        super.remove(comment);
    }

    @Override
    public List<Comment> findCommentsofUser(String username) {
        TypedQuery<Comment> query = em.createNamedQuery("comment.findByUsername", Comment.class);
        query.setParameter("name", username);
        List<Comment> result = query.getResultList();
        return result;    
    }

    @Override
    public List<Comment> findAll() {
        return super.findAll();
    }
    
}
