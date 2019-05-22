/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.JPA;
import DAO.StepDAOJPA;
import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.Step;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Damhuis
 */
public class StepService {
    
    @Inject
    @JPA
    public StepDAOJPA stepDAOJPA;

    public void create(Step step) {
        stepDAOJPA.create(step);
    }

    public void edit(Step step) {
        stepDAOJPA.edit(step);
    }

    public void remove(Long id) {
        stepDAOJPA.remove(id);
    }

    public Step findByName(String name) {
        return stepDAOJPA.findByName(name);
    }

    public List<Step> getAllSteps() {
        return stepDAOJPA.findAll();
    }
    
    public void addComment(Step s,Comment c){
        stepDAOJPA.addComment(s, c);
    }
    
    public void removeComment(Step s, Comment c){
        stepDAOJPA.removeComment(s, c);
    }
}
