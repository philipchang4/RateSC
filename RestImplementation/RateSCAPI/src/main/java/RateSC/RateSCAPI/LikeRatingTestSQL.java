package RateSC.RateSCAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LikeRatingTestSQL {
	public static void main(String args[]) {
		Rating rating= DataModify.getData().likeRating(1);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String stringRating = gson.toJson(rating);
		System.out.println(stringRating);
	}
}
