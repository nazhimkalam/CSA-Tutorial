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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * @author Mr.Nazhim
 */

// To test out your api:
// Please open postman and use the following endpoint http://localhost:8080/HelloWorld1/rest/products
// HelloWorld1 is in the URL path since thats your project name
// Right click on thee project and select RUN.

// Don't worry if you see a page loading with the text "Hello world!", this is just the index.html page from the /Web Pages dir

@Path("/products")
public class ProductResource {

    private final ArrayList<Product> products = new ArrayList<>();

    // Constructor to initialize the data here.
    public ProductResource() {
        addDummyProductData();
    }

    // Get all products
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        // isEmpty is a method from ArrayList class which checks if the list is empty or not, by returning a boolean
        if (!products.isEmpty()) {
            return Response.status(Response.Status.OK).entity(products).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity(products).build();
        }
    }

    // Get a specific products by ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        Product product = findProductById(id);

        if (product != null) {
            return Response.status(Response.Status.OK).entity(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Create a new product (Consumes mean we are sending a JSON body & produces mean we are recieving a JSON response)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        product.setId(generateId());

        products.add(product);

        ArrayList<Product> updatedProducts = new ArrayList<>(products);

        return Response.status(Response.Status.CREATED).entity(updatedProducts).build();
    }

    // Update an existing product (In this case we pass the ID of the product to be updated in the path, along with the updated content body)
    // There are 2 types of params: 
    // 1. PathParam -> localhost:8080/HelloWorld1/rest/products/1
    // 2. QueryParam -> localhost:8080/HelloWorld1/rest/products?id=1
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@QueryParam("id") int id, @QueryParam("name") String name) {
        Product existingProduct = findProductById(id);

        if (existingProduct != null) {
            // Update name if present in the request body
            if (name != null) {
                existingProduct.setName(name);
            }

            return Response.status(Response.Status.OK).entity(existingProduct).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Delete an existing product
    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        Product product = findProductById(id);

        if (product != null) {
            products.remove(product);

            return Response.status(Response.Status.OK).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Helper method to generate dummy products:- We will normally need to store the data into a file or database for read & write operations.
    // Since the server will be serving static content not dynamic
    private void addDummyProductData() {
        Product p1 = new Product(1, "Soap");
        Product p2 = new Product(2, "Brush");

        products.add(p1);
        products.add(p2);
    }

    // Helper method to generate unique IDs
    private int generateId() {
        int maxId = 0;

        for (Product product : products) {
            maxId = Math.max(maxId, product.getId());
        }
        return maxId + 1;
    }

    // Helper method to find a product by ID
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
