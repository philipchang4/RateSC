package RateSC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale.Category;

public class DataModify {
	
	private static DataModify dm = new DataModify();
	public static DataModify getData() {
		return dm;
	}
	public Category getCategory (){ 
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/...user=...&password=...&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT....");
			//return
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	public ArrayList<Rating> getRatings() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/...user=...&password=...&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating");
			while(rs.next()) {
				String category = rs.getString("category");
				String description = rs.getString("description");
				String dateCreated  = rs.getString("date");
				int numLikes = rs.getInt("likes");
				Double rating = rs.getDouble("rating");
				boolean privacy = rs.getBoolean("privacy");
				Category c = new Category();
				Rating r = new Rating(c, description, dateCreated, numLikes, rating, privacy);
				allRatings.add(r);
			}
			return allRatings;
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public void addCategory(String Category) {
		//check to see if cateory isn't already in data
		//if it is, print message that category already exists
		//if not, put into database
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("");
			st = conn.createStatement();
			rs = st.executeQuery("INSERT...");
			//return
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	public void addRating(String Category, String description, int num, Double rating,
			boolean privacy) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/...user=...&password=...&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("INSERT...");
			//return
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	
	}
	public void likeRating() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/...user=...&password=...&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("UPDATE...");
			//return
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	}
}
