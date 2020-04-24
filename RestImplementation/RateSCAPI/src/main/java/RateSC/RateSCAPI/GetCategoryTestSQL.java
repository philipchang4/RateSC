package RateSC.RateSCAPI;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import apple.laf.JRSUIConstants.State;

public class GetCategoryTestSQL {
	 public static void main (String args[]) {
		ArrayList<Category> allCategories = DataModify.getData().getCategory();
		String categories = new Gson().toJson(allCategories);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyCategoryString = gson.toJson(allCategories);
		System.out.println(prettyCategoryString);
	 }
}
