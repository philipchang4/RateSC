package RateSC.RateSCAPI;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class RatedObject {
	Double avgRating;
	String name;
	Integer RatedObjectID;
	List<Rating> listRatings;
	
	RatedObject(String name, Double avgRating, Integer RatedObjectID, List<Rating> listRatings) {
		this.name= name;
		this.avgRating = avgRating;
		this.RatedObjectID = RatedObjectID;
		this.listRatings= listRatings;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public double computeAvgRating() {
		if (listRatings.size() != 0) {
			Double sum = 0.0;
			for(Rating r: listRatings) {
				sum += r.getRating();
			}
			this.avgRating = sum/(listRatings.size());
		}
		else {
			this.avgRating = 0.0;
		}
		return this.avgRating;
	}
	
}
