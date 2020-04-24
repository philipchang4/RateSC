package RateSC.RateSCAPI;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("GetCategories")
public class CategoriesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategories() {
		System.out.println("GetCategories");
		ArrayList<Category> allCategories = DataModify.getData().getCategory();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String categories = gson.toJson(allCategories);
		return categories;
	}
	
	
}
