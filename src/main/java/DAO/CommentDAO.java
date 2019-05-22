/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mycompany.travelpoint.domain.Comment;
import java.util.List;

/**
 *
 * @author Damhuis
 */
public interface CommentDAO {

    void create(Comment comment);

    void remove(Comment comment);

    List<Comment> findCommentsofUser (String username);
}
