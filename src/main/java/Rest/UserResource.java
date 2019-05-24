/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Services.UserService;
import com.mycompany.travelpoint.domain.User;
import io.swagger.annotations.Api;
import java.net.URI;
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
@Path("users")
@Api
@Stateless
public class UserResource {

    @Inject
    private UserService us;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        GenericEntity ge = new GenericEntity<List<User>>(us.findAll()){     
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
        User user = us.findbyUsername(name);
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(user).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Long x = Long.valueOf(id);
        User user = us.findbyId(x);
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(user).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        us.createUser(user);
        URI id = URI.create(user.getUsername());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") long id) {
        User u = us.findbyId(id);
        us.remove(u);
        return Response.noContent().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editUser(User user) {
        User founduser = us.findbyUsername(user.getUsername());
        if(founduser == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        us.edit(user);
        return Response.ok(founduser).build();
    }
}
