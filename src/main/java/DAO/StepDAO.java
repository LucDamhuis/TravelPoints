/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Step;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public interface StepDAO {
    
    void create(Step step);
    void remove (Step step);
    Step findByName(String name);
    
}
