/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Services.StepService;
import com.mycompany.travelpoint.domain.Step;
import io.swagger.annotations.Api;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author Damhuis
 */
@Path("steps")
@Api
@Stateless
public class StepResource {
    
    @Inject
    private StepService ss;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSteps() {
        GenericEntity ge = new GenericEntity<List<Step>>(ss.getAllSteps()){    
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response geByName(@PathParam("name") String name) {
        Step step = ss.findByName(name);
        if(step == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(ss).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createStep(Step step) {
        if(step == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ss.create(step);
        URI id = URI.create(step.getName());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{name}")
    public Response deleteStep(@PathParam("name") String name) {
        Step step = ss.findByName(name);
        if(step==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ss.remove(step.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editStep(Step step) {
        Step foundStep = ss.findByName(step.getName());
        if(foundStep == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ss.edit(step);
        return Response.ok(foundStep).build();
    }
    
}
