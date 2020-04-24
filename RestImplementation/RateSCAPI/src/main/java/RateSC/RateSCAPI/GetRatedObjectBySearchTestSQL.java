package RateSC.RateSCAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetRatedObjectBySearchTestSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RatedObject RatedObjectByName= DataModify.getData().getRatedObjectByName("Classes", "Math");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rating = gson.toJson(RatedObjectByName);
		System.out.println(rating);
	}
}
