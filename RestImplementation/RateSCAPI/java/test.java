 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("test")
public class test {
	/**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }


    /**
     * Retrieves representation of an instance of test
     * @return an instance of String
     */
	@GET
	@Produces("text/plain")
	public String resourceMethodGET() { 
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/**
     * PUT method for updating or creating an instance of test
     * @content content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
	@PUT
	@Consumes("text/plain")
	public void resourceMethodPUT(String content) { 
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}