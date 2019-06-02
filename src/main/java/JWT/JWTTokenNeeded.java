/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JWT;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.ws.rs.NameBinding;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Target;



/**
 *
 * @author Damhuis
 */
@NameBinding
@Retention(RUNTIME)
@Target(value = {TYPE, METHOD})
public @interface JWTTokenNeeded {
    public String[] value() default {};
}
