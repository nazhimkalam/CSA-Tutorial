package lk.iit.tutorial;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * @author Mr.Nazhim
 */

// To test out your api:
// Please open postman and use the following endpoint http://localhost:8080/HelloWorld1/rest/customers
// HelloWorld1 is in the URL path since thats your project name
// Right click on thee project and select RUN.

// Don't worry if you see a page loading with the text "Hello world!", this is just the index.html page from the /Web Pages dir

@Path("/customers")
public class CustomerResource {

    private final ArrayList<Customer> customers = new ArrayList<>();

    // Constructor to initialize the data here.
    public CustomerResource() {
        addDummyCustomerData();
    }

    // Get all customers
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        // isEmpty is a method from ArrayList class which checks if the list is empty or not, by returning a boolean
        if (!customers.isEmpty()) {
            return Response.status(Response.Status.OK).entity(customers).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity(customers).build();
        }
    }

    // Get a specific customer by ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        Customer customer = findCustomerById(id);

        if (customer != null) {
            return Response.status(Response.Status.OK).entity(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Create a new customer (Consumes mean we are sending a JSON body & produces mean we are recieving a JSON response)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        customer.setId(generateId());

        customers.add(customer);

        ArrayList<Customer> updatedCustomers = new ArrayList<>(customers);

        return Response.status(Response.Status.CREATED).entity(updatedCustomers).build();
    }

    // Update an existing customer (In this case we pass the ID of the customer to be updated in the path, along with the updated content body)
    // There are 2 types of params: 
    // 1. PathParam -> localhost:8080/HelloWorld1/rest/customers/1
    // 2. QueryParam -> localhost:8080/HelloWorld1/rest/customers?id=1
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        Customer existingCustomer = findCustomerById(id);

        if (existingCustomer != null) {
            // Update name if present in the request body
            if (customer.getName() != null) {
                existingCustomer.setName(customer.getName());
            }

            // Update age if present in the request body
            if (customer.getAge() != 0) {
                existingCustomer.setAge(customer.getAge());
            }

            return Response.status(Response.Status.OK).entity(existingCustomer).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Delete an existing customer
    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer customer = findCustomerById(id);

        if (customer != null) {
            customers.remove(customer);

            return Response.status(Response.Status.OK).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Helper method to generate dummy customers:- We will normally need to store the data into a file or database for read & write operations.
    // Since the server will be serving static content not dynamic
    private void addDummyCustomerData() {
        Customer c1 = new Customer(1, "Nazhim", 25);
        Customer c2 = new Customer(2, "John", 23);
        Customer c3 = new Customer(3, "Sam", 18);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
    }

    // Helper method to generate unique IDs
    private int generateId() {
        int maxId = 0;

        for (Customer customer : customers) {
            maxId = Math.max(maxId, customer.getId());
        }
        return maxId + 1;
    }

    // Helper method to find a customer by ID
    private Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}
