package RateSC.RateSCAPI;

public class Rating {
	private Integer ratedObjectID;
	private String description;
	private String dateCreated;
	private int ID;
	private int numLikes;
	private double rating;
	private boolean privacy;
	
	public Rating(Integer ratedObjectID, String description, String dateCreated, int numLikes,
			double rating, boolean privacy, Integer ID) {
		this.ratedObjectID = ratedObjectID;
		this.description = description;
		this.dateCreated = dateCreated;
		this.numLikes = numLikes;
		this.rating= rating;
		this.privacy = privacy;
		this.ID=ID;
		
		
	}
	
	public Integer getRatedObjectID() {
		return ratedObjectID;
	}



	public void setRatedObjectID(Integer ratedObjectID) {
		this.ratedObjectID = ratedObjectID;
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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
}
