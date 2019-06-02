/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Services.TripService;
import com.mycompany.travelpoint.domain.Trip;
import io.swagger.annotations.Api;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import HATEOS.Link;
import JWT.JWTTokenNeeded;
import com.mycompany.travelpoint.domain.Step;
import java.util.Set;
import javax.ws.rs.core.*;
import javax.ws.rs.core.UriBuilder.*;

/**
 *
 * @author Damhuis
 */
@Path("trips")
@Api
@Stateless
public class TripResource {

    @Inject
    private TripService ts;

    @Context
    private UriInfo uriInfo;

    @GET
    @JWTTokenNeeded({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrips() {
        List<Trip> trips = ts.getAllTrips();
        for (Trip trip : trips) {
            String link = getUriForSelf();
            Link newSelfLink = new Link(link, "self");
            Link stepsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + trip.getId().toString() + "/steps", "steps");
            Link userLink = new Link("http://localhost:8080/TravelPoint/api/users/" + trip.getTripTaker().getId().toString() + "/id", "triptaker");
            Set<Link> links = trip.getLinks();
            links.add(newSelfLink);
            links.add(stepsLink);
            links.add(userLink);
            trip.setLinks(links);
        }
        return Response.ok(trips).build();
    }

    @GET
    @JWTTokenNeeded({"User"})
    @Path("{id}/userid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTripsOfUser(@PathParam("id") Long id) {
        List<Trip> trips = ts.getAllTripsOfUser(id);
         for (Trip trip : trips) {
            String link = getUriForSelf();
            Link newSelfLink = new Link(link, "self");
            Link stepsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + trip.getId().toString() + "/steps", "steps");
            Link userLink = new Link("http://localhost:8080/TravelPoint/api/users/" + trip.getTripTaker().getId().toString() + "/id", "triptaker");
            Set<Link> links = trip.getLinks();
            links.add(newSelfLink);
            links.add(stepsLink);
            links.add(userLink);
            trip.setLinks(links);
        }
        return Response.ok(trips).build();
    }

    @GET
    @JWTTokenNeeded({"User"})
    @Path("{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response geByID(@PathParam("id") Long id) {
        Trip trip = ts.findById(id);
        String link = getUriForSelf();
        Link stepsLink = new Link("http://localhost:8080/TravelPoint/api/trips/" + id + "/steps", "steps");
        Link userLink = new Link("http://localhost:8080/TravelPoint/api/users/" + trip.getTripTaker().getId().toString() + "/id", "triptaker");
        Set<Link> links = trip.getLinks();
        links.add(stepsLink);
        links.add(userLink);
        trip.setLinks(links);
        if (ts == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(trip).build();
    }

    @GET
    @JWTTokenNeeded({"User"})
    @Path("{id}/steps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStepsOfTrip(@PathParam("id") long id) {
        Trip trip = ts.findById(id);
        List<Step> steps = trip.getSteps();
        return Response.ok(steps).build();
    }

    @POST
    @JWTTokenNeeded({"User"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("create")
    public Response createTrip(Trip trip) {
        if (trip == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.create(trip);
        URI id = URI.create(trip.getName());
        return Response.created(id).build();
    }

    @DELETE
    @JWTTokenNeeded({"User"})
    @Path("{name}")
    public Response deleteTrip(@PathParam("name") String name) {
        Trip trip = ts.findByName(name);
        if (trip == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.remove(trip.getId());
        return Response.noContent().build();
    }

    @PUT
    @JWTTokenNeeded({"User"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editTrip(Trip trip) {
        Trip foundtrip = ts.findByName(trip.getName());
        if (foundtrip == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.edit(trip);
        return Response.ok(foundtrip).build();
    }

    private String getUriForSelf() {
        return uriInfo.getAbsolutePathBuilder()
                .build()
                .toString();
    }

}
