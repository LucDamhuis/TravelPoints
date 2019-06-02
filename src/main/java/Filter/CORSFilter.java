/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Damhuis
 */
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        if (headers.get("access-control-expose-headers") == null) {
            headers.add("access-control-expose-headers", ",Authorization" + ",Content-Length");
        }
        if (headers.get("Access-Control-Allow-Origin") == null) {
            headers.add("Access-Control-Allow-Origin", "*");
        }
        if (headers.get("Access-Control-Allow-Methods") == null) {
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        }
        if (headers.get("Access-Control-Allow-Headers") == null) {
            headers.add("Access-Control-Allow-Headers",
                    "Origin"
                    + ", email"
                    + ", password"
                    + ",X-Requested-With"
                    + ",x-access-token"
                    + ",Content-Type"
                    + ",Authorization"
                    + ",Accept");
        }
    }

    
}
