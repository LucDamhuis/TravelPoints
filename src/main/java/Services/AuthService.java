/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import JWT.KeyGenerator;
import com.mycompany.travelpoint.domain.Role;
import com.mycompany.travelpoint.domain.User;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Damhuis
 */
@Stateless
public class AuthService {

    @Inject
    private UserService us;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    public String issueToken(String username, Long duration) {
        User u = us.findbyUsername(username);
        String id = u.getId().toString();
        Role role = u.getRole();
        String rolesString = role.getName();

        Key key = keyGenerator.generateKey();
        return Jwts.builder()
                .claim("role", rolesString)
                .setSubject(id)
                .setId("2")
                .setIssuedAt(new Date())
                .setExpiration(this.toDate(LocalDateTime.now().plusMinutes(duration)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
