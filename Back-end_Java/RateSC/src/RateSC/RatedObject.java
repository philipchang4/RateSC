package RateSC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatedObject {
	Double avgRating;
	String name;
	List<Rating> listRatings;
	int totalRatings;
	double sumRatings;
	
	RatedObject(String name, Double avgRating) {
		this.name= name;
		this.avgRating = avgRating;
		listRatings= Collections.synchronizedList(new ArrayList<Rating>());
	}
	
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public void computeAvgRating(Double rating) {
		totalRatings++;
		sumRatings += rating;
		avgRating= sumRatings/totalRatings;
	}
	
}
