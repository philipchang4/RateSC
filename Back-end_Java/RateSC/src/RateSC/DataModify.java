package RateSC.RateSCAPI;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class DataModify {
	
	private static DataModify dm = new DataModify();
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
	
	public void getRatedObjects() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM RatedObjects");
			while(rs.next()) {
				String name = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = new RatedObject(name,avg);
				allObjects.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public ArrayList<RatedObject> getRatedObjectsByCategory(String categoryName){
		ArrayList<RatedObject> ObjectsbyCategory = new ArrayList<RatedObject>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE  \n" + 
					"ro.categoryID = c.categoryID AND c.title = " + categoryName);
			while(rs.next()) {
				String title = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = new RatedObject(title, avg);
				ObjectsbyCategory.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return ObjectsbyCategory;
	}
	
	
	public RatedObject getRatedObjectByName(String categoryName, String searchName){
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE\n" + 
					"ro.categoryID = c.categoryID AND c.title = " + categoryName + "AND"
					+ "ro.title =" + searchName);
			while(rs.next()) {
				String title = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = new RatedObject(title, avg);
				if(getRatedObject(ro.getName()) != null) {
					return getRatedObject(ro.getName());
				}
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return null;
	}
	
	public ArrayList<RatedObject> getRatedObjectbyUser(String username) {
		ArrayList<RatedObject> ObjectsbyUser = new ArrayList<RatedObject>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT userID FROM User WHERE User.username =" + username);
			rs.next();
			Integer ID = rs.getInt("userID");
			rs = st.executeQuery("SELECT * from RatedObject WHERE userID=" + ID.toString());
			while(rs.next()) {
				String title = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				RatedObject ro = new RatedObject(title, avg);
				ObjectsbyUser.add(ro);
			}
			return ObjectsbyUser;
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return null;
		
	}
	
	public ArrayList<Rating> getRatingsByRatedObject(String RatedObjectName) {
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Ratings r, RatedObject ro WHERE r.ratedObjectID = ro.ratedObjectID"
					+ "AND ro.title="+ RatedObjectName);
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
			return allRatings;
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return null;
	}
	
	public  ArrayList<Rating> getRatingsByUser(String user_name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
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
				RatedObject ro = getRatedObject(title);
				Rating r = new Rating(ro, description, dateCreated, numLikes, rating, privacy);
				allRatings.add(r);
			}
			return allRatings;
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return null;
	}
	
	
	
	public RatedObject getRatedObject(String title) {
		for(int i = 0; i < allObjects.size();i++) {
			if (allObjects.get(i).getName() == title) {
				return allObjects.get(i);
			}
		}
		return null;
	}
	
	public Rating getRatingbyID(Integer ID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * From Rating WHERE ratingID =" + ID.toString());
			rs.next();
			
		}
		
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
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
			
			int row = st.executeUpdate("INSERT INTO Rating (title, rdescription, dateCreated, numLikes,"
					+ "numRating, privacy) VALUES ('" + Category + "','" + description + "','" + date +
					"',0,'" + rating.toString() + "'," + privacy + ");");
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
	}
	
	public void addRatedObject(String name, String CategoryName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=gymnast_82&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT categoryID FROM Category WHERE title = '" + CategoryName + "'");
			rs.next();
			Integer CID= rs.getInt("categoryID");
			int row = st.executeUpdate("INSERT INTO RatedObject (title, sumRatings, averageRating, totalRatings, categoryID) VALUES ('" + 
			name + "', 0.0, 0.0, 0, " + CID.toString() + ");");
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	
	public void likeRating(Integer rating_ID) {
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
				int row = st.executeUpdate("UPDATE Likes SET numLikes = 1 WHERE userID = likeID");
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
}
