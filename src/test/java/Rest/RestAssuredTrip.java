/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import com.mycompany.travelpoint.domain.Step;
import com.mycompany.travelpoint.domain.Trip;
import com.mycompany.travelpoint.domain.User;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damhuis
 */
public class RestAssuredTrip {

    public RestAssuredTrip() {
    }

    @Before
    public void setUp() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }
        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/rest-garage-sample/";
        }
        RestAssured.basePath = basePath;
        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
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
        given().when().get("trips").then().statusCode(200);
    }

    @Test
    public void addTrip() {

        User user = new User("LucDam", "Luc", "Damhuis", "01-01-1993", "ldamhuis@hotmail.com", null);
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step("teststep", "testdesctription", 10.0, 11.0, 12.0, 13.0, null));
        Trip trip = new Trip(Long.valueOf(1), "test", "desc", user, steps, 20.0, 21.0, 22.0, 23.0, null);

        Trip tr = given().
                contentType("application/json").
                body(user).
                when().
                post("/trips").as(Trip.class);
        assertEquals(trip.getName(), tr.getName());
    }

    @Test
    public void shouldReturnPersonForTheId() {

    }

    @Test
    public void deleteStudent() {

    }
}
