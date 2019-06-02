/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSockets;

import com.google.gson.Gson;
import com.mycompany.travelpoint.domain.Comment;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Damhuis
 */
public class GsonEncoder implements Encoder.Text<Comment> {

    private final Gson gson = new Gson();

    @Override
    public String encode(Comment t) throws EncodeException {
        return gson.toJson(t,Comment.class);
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

}
