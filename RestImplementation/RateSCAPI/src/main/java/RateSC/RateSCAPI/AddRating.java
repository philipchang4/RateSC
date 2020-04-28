package RateSC.RateSCAPI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("AddRating/{userID}/{RatedObjectID}/{Description}/{Rating}/{Privacy}")
public class AddRating {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addRating(@PathParam("userID") String userID, @PathParam("RatedObjectID") String ratedObjectID, @PathParam("Description") String Description,  @PathParam("Rating") String Rating,
			@PathParam("Privacy") String Privacy) {
		System.out.println("new rating created");
		Integer ratingID = DataModify.getData().addRating(userID, ratedObjectID, Description, Rating, Privacy);
	    Rating newRating = DataModify.getData().getRatingbyID(ratingID);
	    RatedObject ro= DataModify.getData().getRatedObjectbyID(newRating.getRatedObjectID());
	    double avgRating = ro.computeAvgRating();
	    DataModify.getData().UpdateAverageRating(Integer.parseInt(ratedObjectID), avgRating);
		String rating = new Gson().toJson(newRating);
		return rating;
	}
}
