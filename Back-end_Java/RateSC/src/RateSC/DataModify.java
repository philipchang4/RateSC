package RateSC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class DataModify {
	
	private static DataModify dm = new DataModify();
	ArrayList<Rating> allRatings = new ArrayList<Rating>();
	
	
	public static DataModify getData() {
		return dm;
	}
	
	public ArrayList<Category> getCategory (){ 
		ArrayList<Category> allCategories = new ArrayList<Category>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM Category");
			while(rs.next()) {
				String title = rs.getString("title");
				Category c = new Category(title);
				allCategories.add(c);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return allCategories;
	}
	
	public ArrayList<RatedObject> getRatedObjectbyCategory(String categoryName){
		ArrayList<RatedObject> ObjectsbyCategory = new ArrayList<RatedObject>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE  \n" + 
					"ro.categoryID = c.categoryID");
			while(rs.next()) {
				String title = rs.getString("title");
				//Double sum = rs.getDouble("sumRatings");
				Double avg = rs.getDouble("averageRating");
				//int total = rs.getInt("totalRatings");
				RatedObject ro = new RatedObject(title, avg);
				ObjectsbyCategory.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return ObjectsbyCategory;
	}
	public ArrayList<RatedObject> getRatedObjectbyName(String categoryName, String searchName){
		ArrayList<RatedObject> ObjectsbyName = new ArrayList<RatedObject>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE\n" + 
					"ro.categoryID = c.categoryID");
			while(rs.next()) {
				String title = rs.getString("title");
				//Double sum = rs.getDouble("sumRatings");
				Double avg = rs.getDouble("averageRating");
				//int total = rs.getInt("totalRatings");
				RatedObject ro = new RatedObject(title, avg);
				ObjectsbyName.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return ObjectsbyName;
	}
	
	public void getRatingsbyRatedObject(ArrayList<RatedObject> ObjectsinCategory) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Ratings r, RatedObject ro WHERE r.ratedObjectID = ro.ratedObjectID");
			while(rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("date");
				int numLikes = rs.getInt("likes");
				Double rating = rs.getDouble("rating");
				boolean privacy = rs.getBoolean("privacy");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = getRatedObject(title, ObjectsinCategory);
				Rating r = new Rating(ro, description, dateCreated, numLikes, rating, privacy);
				allRatings.add(r);
			}
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public void getRatingsbyName(String user_name, ArrayList<RatedObject> ObjectsbyName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT userID FROM User WHERE User-name = username");
			rs.next();
			Integer id = rs.getInt("userID");
			rs = st.executeQuery("SELECT * FROM Ratings r, WHERE ro.userID =" + id.toString());
			while(rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("date");
				int numLikes = rs.getInt("likes");
				Double rating = rs.getDouble("rating");
				boolean privacy = rs.getBoolean("privacy");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = getRatedObject(title, ObjectsbyName);
				Rating r = new Rating(ro, description, dateCreated, numLikes, rating, privacy);
				allRatings.add(r);
			}
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	
	
	public RatedObject getRatedObject(String title, ArrayList<RatedObject> Objects) {
		for(int i = 0; i < allRatings.size();i++) {
			if (Objects.get(i).getName() == title) {
				return Objects.get(i);
			}
		}
		return null;
	}
	
	public void addRating(String Category, String description, int num, Double rating,
			boolean privacy) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			Calendar c = Calendar.getInstance();
			String date = c.getTime().toString();
			
			rs = st.executeQuery("INSERT INTO Rating (title, rdescription, dateCreated, numLikes,"
					+ "numRating, privacy) VALUES ('" + Category + "','" + description + "','" + date +
					"',0,'" + rating.toString() + "'," + privacy + ");");
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	}
	
	public void addRatedObject(String name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("INSERT INTO RatedObject (title, sumRatings, averageRating, totalRatings) VALUES ('" + 
			name + "', 0.0, 0.0, 0);");
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public void likeRating(String ro_name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Likes i, Ratings r, User u WHERE\n" + 
					"i.ratingID = r.ratinID AND i.userID=u.userID");
			int count = 0;
			while (rs.next()) {
				Integer numRatings = rs.getInt("numLikes");
			}
			if (count == 0) {
				rs = st.executeQuery("UPDATE Likes SET numLikes = 1 WHERE userID = likeID");
			}
			
			
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	}
}
