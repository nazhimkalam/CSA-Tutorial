    package Ik.iit.tutorial;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/*
 * @author Mr.Nazhim
 */
public class StudentApplication extends ResourceConfig {
    public StudentApplication() {
        register(StudentResource.class);
        
        property(ServerProperties.TRACING, "ALL");
    }
}
