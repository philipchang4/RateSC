package RateSC.RateSCAPI;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("AddRating/{ROName}")
public class AddRating {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addRating(@PathParam("ROName") String ratedObjectName, @PathParam("CategoryName") String CategoryName, Rating new_rating) {
		System.out.println("new rating created");
		int newRowID= DataModify.getData().addRating(CategoryName, new_rating.getDescription(), new_rating.getNumLikes(), new_rating.getRating() ,
				new_rating.isPrivacy());
		Rating newRating = DataModify.getData().getRatingbyID(newRowID);
		String rating = new Gson().toJson(newRating);
		return rating;
	}
	
}
