package RateSC.RateSCAPI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DataModify {
	
	private static DataModify dm = new DataModify();
	ArrayList<RatedObject> allObjects = new ArrayList<RatedObject>();
	
	
	public static DataModify getData() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dm;
	}
	
	public ArrayList<Category> getCategory (){ 
		ArrayList<Category> allCategories = new ArrayList<Category>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM Category");
			while(rs.next()) {
				String title = rs.getString("title");
				Category c = new Category(title);
				allCategories.add(c);
				System.out.println(title);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return allCategories;
	}
	
//	public void getRatedObjects() {
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
//			st = conn.createStatement();
//			rs = st.executeQuery("SELECT* FROM RatedObjects");
//			while(rs.next()) {
//				String name = rs.getString("title");
//				Double avg = rs.getDouble("averageRating");
//				RatedObject ro = new RatedObject(name,avg);
//				allObjects.add(ro);
//			}
//		}
//		catch(SQLException sqle) {
//			System.out.println("sqle: " + sqle.getMessage());
//		}
//	}
	
	public ArrayList<RatedObject> getRatedObjectsByCategory(String categoryName){
		ArrayList<RatedObject> ObjectsbyCategory = new ArrayList<RatedObject>();
		categoryName= categoryName.replace('+', ' ');
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE  \n" + 
					"ro.categoryID = c.categoryID AND c.title = '" + categoryName +"'");
			while(rs.next()) {
				String title = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				List<Rating> ratingsList= getRatingsByRatedObject(title);
				RatedObject ro = new RatedObject(title, avg, ratingsList);
				ObjectsbyCategory.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return ObjectsbyCategory;
	}
	
	
//	public List<RatedObject> getRatedObjectByName(String categoryName, String searchName){
//		getRatedObjects();
//		
//		
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
//			st = conn.createStatement();
//			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE\n" + 
//					"ro.categoryID = c.categoryID AND c.title = '" + categoryName + "' AND "
//					+ "ro.title = '" + searchName +"'");
//			rs.next();
//			String title = rs.getString("title");
//			Double avg = rs.getDouble("averageRating");
//			RatedObject ro = new RatedObject(title, avg);
//			return ro;
//			}
//		}
//		catch(SQLException sqle) {
//			System.out.println("sqle: " + sqle.getMessage());
//		}
//		return null;
//	}
	
//	public ArrayList<RatedObject> getRatedObjectbyUser(String username) {
//		ArrayList<RatedObject> ObjectsbyUser = new ArrayList<RatedObject>();
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
//			st = conn.createStatement();
//			rs = st.executeQuery("SELECT userID FROM User WHERE User.username = '" + username + "'");
//			rs.next();
//			Integer ID = rs.getInt("userID");
//			rs = st.executeQuery("SELECT * from RatedObject WHERE userID=" + ID.toString());
//			while(rs.next()) {
//				String title = rs.getString("title");
//				Double avg = rs.getDouble("averageRating");
//				RatedObject ro = new RatedObject(title, avg);
//				ObjectsbyUser.add(ro);
//			}
//			return ObjectsbyUser;
//		}
//		catch(SQLException sqle) {
//			System.out.println("sqle: " + sqle.getMessage());
//		}
//		return null;
//	}
	
	public ArrayList<Rating> getRatingsByRatedObject(String RatedObjectName) {
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating r, RatedObject ro WHERE r.ratedObjectID = ro.ratedObjectID"
					+ " AND ro.title='"+ RatedObjectName +"'" );
			while(rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("dateCreated");
				int numLikes = rs.getInt("numLikes");
				Double rating = rs.getDouble("numRating");
				boolean privacy = rs.getBoolean("privacy");
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT userID FROM User WHERE User-name = username");
			rs.next();
			Integer id = rs.getInt("userID");
			rs = st.executeQuery("SELECT * FROM Ratings r, WHERE ro.userID =" + id.toString());
			while(rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("dateCreated");
				int numLikes = rs.getInt("numLikes");
				Double rating = rs.getDouble("numRating");
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * From Rating WHERE ratingID =" + ID.toString());
			rs.next();
			String title = rs.getString("title");
			String description = rs.getString("rdescription");
			String dateCreated  = rs.getString("dateCreated");
			int numLikes = rs.getInt("numLikes");
			Double rating = rs.getDouble("numRating");
			boolean privacy = rs.getBoolean("privacy");
			RatedObject ro = getRatedObject(title);
			Rating r = new Rating(ro, description, dateCreated, numLikes, rating, privacy);
			return r;
			
		}
		
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
		return null;
	}
	
	
	public int addRating(String RatedObject, String description, int num, Double rating,
			boolean privacy) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Integer row = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			Calendar c = Calendar.getInstance();
			String date = c.getTime().toString();
			
			row = st.executeUpdate("INSERT INTO Rating (title, rdescription, dateCreated, numLikes,"
					+ "numRating, privacy) VALUES ('" + RatedObject + "','" + description + "','" + date +
					"',0,'" + rating.toString() + "'," + privacy + ");");
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}
		return row;
	}
	
	public void addRatedObject(String name, String CategoryName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
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
	
	public Rating likeRating(Integer rating_ID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating WHERE ratingID = " +  rating_ID.toString());
			rs.next();
			Integer numRatings = rs.getInt("numLikes")+1;
			int row = st.executeUpdate("UPDATE Rating SET numLikes = " + numRatings.toString() + " WHERE ratingID=" + rating_ID.toString());
			return getRatingbyID(rating_ID);
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		return null;
	}
}



