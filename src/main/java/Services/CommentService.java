/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.CommentDAO;
import DAO.CommentDAOJpa;
import DAO.JPA;
import com.mycompany.travelpoint.domain.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
@Stateless
public class CommentService {
    @Inject
    @JPA
    CommentDAO commentDAOJpa; 
    
    @Inject 
    StepService stepService;
    
    public void create(Comment c){
        commentDAOJpa.create(c);
    }
    
    public void remove(Comment c) {
        commentDAOJpa.remove(c);
    }
    
    public List<Comment> findAll(){
        return commentDAOJpa.findAll();
    }
    
    public List<Comment> findAllOfUser(String username){
        return commentDAOJpa.findCommentsofUser(username);
    }
}
