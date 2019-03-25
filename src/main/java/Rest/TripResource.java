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
import javax.ws.rs.core.*;

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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrips() {
        GenericEntity ge = new GenericEntity<List<Trip>>(ts.getAllTrips()){    
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response geByName(@PathParam("name") String name) {
        Trip trip = ts.findByName(name);
        if(ts == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(ts).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(Trip trip) {
        if(trip == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.create(trip);
        URI id = URI.create(trip.getName());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{name}")
    public Response deleteUser(@PathParam("name") String name) {
        Trip trip = ts.findByName(name);
        if(trip==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.remove(trip.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editUser(Trip trip) {
        Trip foundtrip = ts.findByName(trip.getName());
        if(foundtrip == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.edit(trip);
        return Response.ok(foundtrip).build();
    }
}
