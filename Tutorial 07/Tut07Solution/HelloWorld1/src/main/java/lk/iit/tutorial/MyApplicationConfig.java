package lk.iit.tutorial;

import org.glassfish.jersey.server.ResourceConfig;

/*
 * @author Mr.Nazhim
 */

public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig() {
        // register any newly created resources here
        
        register(CustomerResource.class);
        register(ProductResource.class);
    }
}