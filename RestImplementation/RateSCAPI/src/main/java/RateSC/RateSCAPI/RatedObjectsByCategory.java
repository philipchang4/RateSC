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
public class RatedObjectsByCategory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatedObjectsByCategory(@PathParam("CategoryName") String CategoryID) {
		System.out.println("GETRatingsByRatedObjectResource called");
		ArrayList<RatedObject> RO = DataModify.getData().getRatedObjectsByCategory(Integer.parseInt(CategoryID));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(RO);
		return rating;
	}
}