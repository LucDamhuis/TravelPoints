/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import io.swagger.jaxrs.config.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Damhuis
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application{
    public JAXRSConfiguration(){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("2.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/Travelpoint/api");
        beanConfig.setResourcePackage("s6.travelpoint.rest");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan();
    }

}
