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

@Path("RatedObjectBySearch/{SearchTerm}")
public class RatedObjectBySearch {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRatedObjectByName(@PathParam("SearchTerm") String searchTerm) {
		System.out.println("GETRatingsByRatedObjectResource called");
		List<RatedObject> AllRatedObjects= DataModify.getData().getRatedObjects();
		List<RatedObject> resultsList= new ArrayList<RatedObject>();
		searchTerm= searchTerm.toLowerCase();
		searchTerm= searchTerm.replace(" ", "");
		for (RatedObject ratedobject : AllRatedObjects) {
			String trimmedString= ratedobject.getName().replace(" ", "");
			if (trimmedString.toLowerCase().contains(searchTerm)) {
				resultsList.add(ratedobject);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String ratedObjectsBySearch = gson.toJson(resultsList);
		return ratedObjectsBySearch;
		
	}
	
}