package RateSC.RateSCAPI;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import apple.laf.JRSUIConstants.State;

public class GetRatedObjectByCategoryTestSQL {
	 public static void main (String args[]) {
		 ArrayList<RatedObject> RO = DataModify.getData().getRatedObjectsByCategory("Classes");
		 for (RatedObject Ro : RO) {
			 Ro.computeAvgRating();
		 }
	 	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 String prettyCategoryString = gson.toJson(RO);
		 System.out.println(prettyCategoryString);
	 }
}
