/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import HATEOS.Link;
import JWT.JWTTokenNeeded;
import Services.AuthService;
import Services.UserService;
import com.mycompany.travelpoint.domain.User;
import io.swagger.annotations.Api;
import java.net.URI;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Damhuis
 */
@Path("users")
@Api
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserService us;

    @Inject
    AuthService as;

    @GET
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = us.findAll();
        for (User user : users) {
            Set<Link> links = user.getLinks();
            Link selfLink = new Link(getUriForSelf() + "/" + user.getId().toString() + "/id", "self");
            Link tripsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + user.getId().toString() + "/userid", "trips");
            Link followingLink = new Link("http://localhost:8080/TravelPoint/api/users/" + user.getId().toString() + "/following", "following");
            links.add(selfLink);
            links.add(tripsLink);
            links.add(followingLink);
            user.setLinks(links);
        }
        return Response.ok(users).build();
    }

    @GET
    @Path("{id}/following")
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("id") long id) {
        List<User> following = us.getFollowing(id);
        if (following == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        for (User user : following) {
            Set<Link> links = user.getLinks();
            Link selfLink = new Link(getUriForSelf() + "/" + user.getId().toString() + "/id", "self");
            Link tripsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + user.getId().toString() + "/userid", "trips");
            Link followingLink = new Link("http://localhost:8080/TravelPoint/api/users/" + user.getId().toString() + "/following", "following");
            links.add(selfLink);
            links.add(tripsLink);
            links.add(followingLink);
            user.setLinks(links);
        }
        return Response.ok(following).build();
    }

    @GET
    @JWTTokenNeeded({"User"})
    @Path("{name}/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
        User user = us.findbyUsername(name);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Set<Link> links = user.getLinks();
        Link selfLink = new Link(getUriForSelf() + "/" + user.getId().toString() + "/id", "self");
        Link tripsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + user.getId().toString() + "/userid", "trips");
        Link followingLink = new Link("http://localhost:8080/TravelPoint/api/users/" + user.getId().toString() + "/following", "following");
        links.add(selfLink);
        links.add(tripsLink);
        links.add(followingLink);
        user.setLinks(links);
        return Response.ok(user).build();
    }

    @GET
    @Path("{id}/id")
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Long x = Long.valueOf(id);
        User user = us.findbyId(x);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Set<Link> links = user.getLinks();
        Link selfLink = new Link(getUriForSelf() + "/" + user.getId().toString() + "/id", "self");
        Link tripsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + user.getId().toString() + "/userid", "trips");
        Link followingLink = new Link("http://localhost:8080/TravelPoint/api/users/" + user.getId().toString() + "/following", "following");
        links.add(selfLink);
        links.add(tripsLink);
        links.add(followingLink);
        user.setLinks(links);
        return Response.ok(user).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("create")
    public Response createUser(User user) {
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        us.createUser(user);
        return Response.ok(user).build();
    }

    @DELETE
    @JWTTokenNeeded({"Admin"})
    @Path("{id}")
    public Response deleteUser(@PathParam("id") long id) {
        User u = us.findbyId(id);
        us.remove(u);
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response getLoginJWT(@HeaderParam("username") String username,
            @HeaderParam("password") String password) {
        System.out.println(username + "    " + password);
        if (us.loginCheckEmail(username, password) || us.loginCheckUsername(username, password)) {
            String token = as.issueToken(username, 15000L);
            return Response.ok("\"" + token + "\"").header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

            //return Response.status(Response.Status.NO_CONTENT).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @PUT
    @JWTTokenNeeded({"User"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editUser(User user) {
        User founduser = us.findbyUsername(user.getUsername());
        if (founduser == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        us.edit(user);
        return Response.ok(founduser).build();
    }

    private String getUriForSelf() {
        return uriInfo.getAbsolutePathBuilder()
                .build()
                .toString();
    }

}
