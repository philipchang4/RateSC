package RateSC.RateSCAPI;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("GetRatingsByUser/{Username}")
public class GetRatingsByUser {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatingsByUser(@PathParam("Username") String username) {
		System.out.println("GETRatingsByRatedObjectResource called");
		List<Rating> RatingsByUser= DataModify.getData().getRatingsByUser(username);
		String rating = new Gson().toJson(RatingsByUser);
		return rating;
	}
}
