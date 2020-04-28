package RateSC.RateSCAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("AddRatedObject/{CategoryID}/{ROName}")
public class AddRatedObject {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addRatedObject(@PathParam("CategoryID") String CategoryID, @PathParam("ROName") String ROName) {
		
	    Integer newRatedObjectID = DataModify.getData().addRatedObject(ROName, CategoryID);
	    System.out.println(newRatedObjectID);
	    RatedObject newRatedObject = DataModify.getData().getRatedObjectbyID(newRatedObjectID);
		String RatedObject = new Gson().toJson(newRatedObject);
		return RatedObject;
	}
	
}
