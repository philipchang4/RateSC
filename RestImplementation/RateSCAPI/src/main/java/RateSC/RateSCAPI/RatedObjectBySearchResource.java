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

@Path("RatedObjectBySearch/{CategoryName}/{SearchName}")
public class RatedObjectBySearchResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatedObjectByName(@PathParam("CategoryName") String CategoryName, @PathParam("SearchName") String searchName) {
		System.out.println("GETRatingsByRatedObjectResource called");
		RatedObject RatedObjectByName= DataModify.getData().getRatedObjectByName(CategoryName, searchName);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(RatedObjectByName);
		return rating;
	}
}