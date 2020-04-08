package RateSC;

public class Rating {
	private RatedObject ratedObject;
	private String description;
	private String dateCreated;
	private int numLikes;
	private double rating;
	private boolean privacy;
	
	public Rating(RatedObject ratedObject, String description, String dateCreated, int numLikes,
			double rating, boolean privacy) {
		this.ratedObject = ratedObject;
		this.description = description;
		this.dateCreated = dateCreated;
		this.numLikes = numLikes;
		this.rating= rating;
		this.privacy = privacy;
		
	}
	
	public RatedObject getRatedObject() {
		return ratedObject;
	}
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
