/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.Step;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public interface StepDAO {

    void edit(Step step);

    void create(Step step);

    void remove(Step step);
    
    void removeById(Long id);
    
    List<Step> findAll();
    
    Step find(Long id);

    Step findByName(String name);

    void addComment(Step s, Comment c);

    void removeComment(Step s, Comment c);

}
