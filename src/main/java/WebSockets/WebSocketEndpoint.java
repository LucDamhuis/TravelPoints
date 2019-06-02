/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSockets;

import com.mycompany.travelpoint.domain.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;

/**
 *
 * @author Damhuis
 */
@ServerEndpoint(
        value = "/comments/{id}/id",
        encoders = GsonEncoder.class,
        decoders = GsonDecoder.class,
        configurator = SessionProvider.class
)
public class WebSocketEndpoint {

    private static final Logger LOG = Logger.getLogger(WebSocketEndpoint.class.getName());

    private static final Map<Long, List<Session>> _sessions = new HashMap<>();

    private HttpSession httpSession;

    private Session session;

    private static final CommentBean COMMENT_BEAN;

    static {
        final String name = "java:global/TravelPoint/CommentBean";
        try {
            COMMENT_BEAN = (CommentBean) new CommentBean();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }

    }

    @OnOpen
    public void onOpen(@PathParam("id") long id, EndpointConfig endpointConfig, Session session) {
        if (!_sessions.containsKey(id)) {
            List<Session> sessions = new ArrayList<>();
            sessions.add(session);
            _sessions.put(id, sessions);
        }
        this.httpSession = SessionProvider.provide(endpointConfig);
        //this.session = session;
        LOG.log(Level.INFO, "onOpen: endpointConfig: {0}, session: {1}", new Object[]{endpointConfig, session});
    }

    @OnMessage
    public void onMessage(Session session, Comment comment) throws EncodeException {
        LOG.log(Level.INFO, "received message with text: {0}", comment.getCommentId());
        for (List<Session> sessions : _sessions.values()) {
            for (Session s : sessions) {
                for (int i = 0; i < 4; i++) {
                    comment.setCommentId(i);
                    COMMENT_BEAN.send(s, comment);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try {
            httpSession.invalidate();
        } catch (IllegalStateException ise) {
            //swallow: httpSession allready expired
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.log(
                Level.WARNING,
                new StringBuilder("an error occured for session ").append(session).toString(),
                throwable
        );
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public Session getSession() {
        return session;
    }
}
