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
	ArrayList<RatedObject> allObjects = new ArrayList<RatedObject>();
	
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
	
	public ArrayList<RatedObject> getRatedObjects(){
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM RatedObject");
			while(rs.next()) {
				String title = rs.getString("title");
				//Double sum = rs.getDouble("sumRatings");
				Double avg = rs.getDouble("averageRating");
				//int total = rs.getInt("totalRatings");
				RatedObject ro = new RatedObject(title, avg);
				allObjects.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return allObjects;
	}
	
	public void getRatings() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM r Rating, ro RatedObject WHERE r.title = ro.title");
			while(rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("date");
				int numLikes = rs.getInt("likes");
				Double rating = rs.getDouble("rating");
				boolean privacy = rs.getBoolean("privacy");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = getRatedObject(title);
				Rating r = new Rating(ro, description, dateCreated, numLikes, rating, privacy);
				allRatings.add(r);
			}
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public RatedObject getRatedObject(String title) {
		for(int i = 0; i < allRatings.size();i++) {
			if (allObjects.get(i).getName() == title) {
				return allObjects.get(i);
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
	
	public void likeRating(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating where title=" + title);
			Integer numRatings = rs.getInt("numLikes");
			rs = st.executeQuery("UPDATE Rating SET numLikes = " + numRatings.toString()  + "WHERE title= " + title);
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	}
}

