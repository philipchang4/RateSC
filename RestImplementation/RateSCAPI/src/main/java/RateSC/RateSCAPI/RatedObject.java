package RateSC.RateSCAPI;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class RatedObject {
	Double avgRating;
	String name;
	List<Rating> listRatings;
	
	RatedObject(String name, Double avgRating, List<Rating> listRatings) {
		this.name= name;
		this.avgRating = avgRating;
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
	public void computeAvgRating() {
		Double sum = 0.0;
		for(Rating r: listRatings) {
			sum += r.getRating();
		}
		this.avgRating = sum/(listRatings.size());
	}
	
}
