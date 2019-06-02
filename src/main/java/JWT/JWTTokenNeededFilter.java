/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.hibernate.cfg.beanvalidation.ValidationMode;

/**
 *
 * @author Damhuis
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Class<?> resourceClass = resourceInfo.getResourceClass();
            JWTTokenNeeded jwtClassAnnotation = resourceClass.getAnnotation(JWTTokenNeeded.class);

            Method resourceMethod = resourceInfo.getResourceMethod();
            JWTTokenNeeded jwtMethodAnnotation = resourceMethod.getAnnotation(JWTTokenNeeded.class);

            Set<String> requiredRoles = new HashSet<>();

            // Add required roles from class annotation.
            if (jwtClassAnnotation != null) {
                String[] roles = jwtClassAnnotation.value();
                for (String role : roles) {
                    if (!requiredRoles.contains(role)) {
                        requiredRoles.add(role);
                    }
                }
            }

            // Add required roles from method annotation.
            if (jwtMethodAnnotation != null) {
                String[] roles = jwtMethodAnnotation.value();
                for (String role : roles) {
                    if (!requiredRoles.contains(role)) {
                        requiredRoles.add(role);
                    }
                }
            }

            Key key = keyGenerator.generateKey();
            String test = key.getEncoded().toString();
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            String tokenRole = claims.get("role", String.class).toLowerCase();
            if (tokenRole == null || tokenRole == "") {
                throw new NotAuthorizedException("Missing roles in token!");
            }
            boolean authorized = false;

            for (String requiredRole : requiredRoles) {
                String role = requiredRole.toLowerCase();
                if (tokenRole.equals(role)) {
                    authorized = true;
                }                
            }
            if(tokenRole.toLowerCase().equals("admin")){
                authorized = true;
            }
            if(!authorized){
                throw new NotAuthorizedException("Acces denied");
            }
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}
