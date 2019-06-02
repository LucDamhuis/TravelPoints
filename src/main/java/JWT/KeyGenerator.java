/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JWT;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;

/**
 *
 * @author Damhuis
 */
@Stateless
public class KeyGenerator {
    
    public Key generateKey() {
        String keyString = "LOCALKEY";
        return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }

    
}
