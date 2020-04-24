package RateSC.RateSCAPI;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("LikeRating/{RatingID}")
public class LikeRating {

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String likeRating(@PathParam("RatingID") Integer RatingID) {
		System.out.println("Add like to rating");
		Rating updatedRating= DataModify.getData().likeRating(RatingID);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(updatedRating);
		return rating;
	}
	
}
