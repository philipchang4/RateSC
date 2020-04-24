package RateSC.RateSCAPI;

import java.util.ArrayList;
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

@Path("RatedObjectByCategory/{CategoryName}")
public class RatedObjectsByCategoryResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatedObjectsByCategory(@PathParam("CategoryName") String CategoryName) {
		System.out.println("GETRatingsByRatedObjectResource called");
		ArrayList<RatedObject> RO = DataModify.getData().getRatedObjectsByCategory(CategoryName);
		for (RatedObject Ro : RO) {
			Ro.computeAvgRating();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(RO);
		return rating;
	}
	
	/*@Path("{SearchName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatedObjectByName(@PathParam("CategoryName") String CategoryName, @PathParam("SearchName") String searchName) {
		System.out.println("GETRatingsByRatedObjectResource called");
		RatedObject RatedObjectByName= DataModify.getData().getRatedObjectByName(CategoryName, searchName);
		String rating = new Gson().toJson(RatedObjectByName);
		return rating;
	}
	
	@Path("addRatedObject/{RatedObjectName}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addRatedObject(@PathParam("CategoryName") String CategoryName, @PathParam("RatedObjectName") String RatedObject, Rating new_rating) {
		System.out.println("new rating created");
		int newRowID= DataModify.getData().addRating(CategoryName, new_rating.getDescription(), new_rating.getNumLikes(), new_rating.getRating() ,
				new_rating.isPrivacy());
		Rating newRating = DataModify.getData().getRatingbyID(newRowID);
		String rating = new Gson().toJson(newRating);
		return rating;
	} */
}