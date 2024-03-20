package lk.iit.tutorial;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/*
 * @author Mr.Nazhim
 */

@ApplicationPath("rest")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        // Add any newly created resource classes here
        
        classes.add(CustomerResource.class);
        classes.add(ProductResource.class);
        
        return classes;
    }
}
