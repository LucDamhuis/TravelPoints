/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.Step;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Damhuis
 */
public class StepDAOColl implements StepDAO {

    CopyOnWriteArrayList<Step> steps = new CopyOnWriteArrayList<>();
    
    @Override
    public void create(Step step) {
        steps.add(step);
    }

    @Override
    public void remove(Step step) {
        steps.remove(step);
    }

    @Override
    public Step findByName(String name) {
        for (Step step : steps) {
            if(step.getName().equals(name)){
                return step;
            }            
        }
        return null;
    }
    
    public List<Step> getByDescription(String description){
        ArrayList<Step> returnSteps = new ArrayList<>();
        for (Step step : steps) {
            if(step.getDescription().contains(description)){
                returnSteps.add(step);
            }            
        }
        return returnSteps;
    } 

    @Override
    public void edit(Step step) {
    }

    @Override
    public Step find(Long id) {
        return null;
    }

    @Override
    public void addComment(Step s, Comment c) {
    }

    @Override
    public void removeComment(Step s, Comment c) {
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<Step> findAll() {
        return null;
    }
}
