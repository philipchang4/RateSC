package RateSC.RateSCAPI;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("GetRatingByRatedObject/{ROName}")
public class RatingsByRatedObjectResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatingsByRatedObject(@PathParam("ROName") String ROName) {
		System.out.println("GETRatingsByRatedObjectResource called");
		List<Rating> RatingsByRatedObject= DataModify.getData().getRatingsByRatedObject(ROName);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(RatingsByRatedObject);
		return rating;
	}
	
}