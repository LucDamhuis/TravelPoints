/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import JWT.JWTTokenNeeded;
import Services.CommentService;
import Services.UserService;
import com.mycompany.travelpoint.domain.Comment;
import com.mycompany.travelpoint.domain.User;
import io.swagger.annotations.Api;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Damhuis
 */
@Path("comments")
@Api
@Stateless
public class CommentResource {

    @Inject
    private CommentService cs;

    @GET
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        GenericEntity ge = new GenericEntity<List<Comment>>(cs.findAll()) {
        };
        
        return Response.ok(ge).build();
    }

    @GET
    @Path("{username}")
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response geByUserName(@PathParam("name") String name) {
        List<Comment> comments = cs.findAllOfUser(name);
        if (comments.isEmpty()) {
            comments = new ArrayList<>();
        }
        return Response.ok(comments).build();
    }

    @POST
    @JWTTokenNeeded({"User"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createComment(Comment comment) {
        if (comment == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        cs.create(comment);
        URI id = URI.create(comment.getUserName());
        return Response.created(id).build();
    }

    @DELETE
    @JWTTokenNeeded({"User"})
    @Path("{id}")
    public Response deleteComment(@PathParam("id") long id) {
        List<Comment> comments = cs.findAll();
        Comment c = null;
        for (Comment comment : comments) {
            if (comment.getCommentId() == id) {
                c = comment;
            }
        }
        cs.remove(c);
        return Response.noContent().build();
    }
}
