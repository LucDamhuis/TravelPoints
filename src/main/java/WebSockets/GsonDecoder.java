/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSockets;
import com.google.gson.Gson;
import com.mycompany.travelpoint.domain.Comment;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
/**
 *
 * @author Damhuis
 */
public class GsonDecoder implements Decoder.Text<Comment>{

    private final Gson gson = new Gson();
    
    @Override
    public Comment decode(String arg0) throws DecodeException {
        return gson.fromJson(arg0, Comment.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }
    
}
