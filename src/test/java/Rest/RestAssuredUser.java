/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;


import com.mycompany.travelpoint.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;


/**
 *
 * @author Damhuis
 */
public class RestAssuredUser {
    
    public RestAssuredUser() {
    }
    

    
    @Before
    public void setUp() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }
		String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/rest-garage-sample/";
        }
        RestAssured.basePath = basePath;
		String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void basicTest() {
        given().when().get("users").then().statusCode(200);
    }
    
    @Test
    public void addUser() {
    
        User user = new User("LucDam", "Luc", "Damhuis", "01-01-1993", "ldamhuis@hotmail.com","password");
        
        User us=given().
            contentType("application/json").
            body(user).
        when().
            post("/users").as(User.class);
        assertEquals(user.getUsername(), us.getUsername());
      }
     
    @Test
    public void shouldReturnPersonForTheId() {
        
    }
    
    @Test
    public void deleteStudent() {
        
    }
     
}
