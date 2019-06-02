/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSockets;

import com.mycompany.travelpoint.domain.Comment;
import java.io.IOException;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.websocket.EncodeException;
import javax.websocket.Session;


/**
 *
 * @author Damhuis
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CommentBean {

    public CommentBean() {
    }
    
    @EJB
    private CommentBean bean;
    
    @Asynchronous
    public void send(Session session,Comment comment){
        try {
            synchronized(session){
                
                session.getBasicRemote().sendObject(comment);
            }
        } catch (IOException | EncodeException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
