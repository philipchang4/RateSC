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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM Category");
			while(rs.next()) {
				String title = rs.getString("title");
				Integer ID= rs.getInt("categoryID");
				Category c = new Category(title, ID);
				allCategories.add(c);
				System.out.println(title);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allCategories;
	}
	
	public List<RatedObject> getRatedObjects() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<RatedObject> allObjects= new ArrayList<RatedObject>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT* FROM RatedObject");
			while(rs.next()) {
				String name = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				Integer RatedObjectID = rs.getInt("RatedObjectID");
				List<Rating> ratings= getRatingsByRatedObject(RatedObjectID.toString());
				RatedObject ro = new RatedObject(name, avg, RatedObjectID, ratings);
				allObjects.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allObjects;
	}
	
	
	
	public ArrayList<RatedObject> getRatedObjectsByCategory(int categoryID){
		ArrayList<RatedObject> ObjectsbyCategory = new ArrayList<RatedObject>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM RatedObject ro, Category c WHERE  \n" + 
					"ro.categoryID = c.categoryID AND c.categoryID = '" + categoryID +"'");
			while(rs.next()) {
				String title = rs.getString("title");
				Double avg = rs.getDouble("averageRating");
				Integer RatedObjectID = rs.getInt("RatedObjectID");
				List<Rating> ratingsList= getRatingsByRatedObject(RatedObjectID.toString());
				RatedObject ro = new RatedObject(title, avg, RatedObjectID, ratingsList);
				ObjectsbyCategory.add(ro);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ObjectsbyCategory;
	}
	
	public ArrayList<Rating> getRatingsByRatedObject(String RatedObjectID ) {
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating r, RatedObject ro WHERE r.ratedObjectID = ro.ratedObjectID AND r.ratedObjectID =" + Integer.parseInt(RatedObjectID));
			while(rs.next()) {
				Integer ratedObjectID = rs.getInt("RatedObjectID");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("dateCreated");
				int numLikes = rs.getInt("numLikes");
				Double rating = rs.getDouble("numRating");
				boolean privacy = rs.getBoolean("privacy");
				Integer ID = rs.getInt("RatingID");
				Rating r = new Rating(ratedObjectID, description, dateCreated, numLikes, rating, privacy, ID);
				allRatings.add(r);
			}
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allRatings;
	}
	
	public  ArrayList<Rating> getRatingsByUser(String user_name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Rating> allRatings = new ArrayList<Rating>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT userID FROM User WHERE User-name = username");
			rs.next();
			Integer id = rs.getInt("userID");
			rs = st.executeQuery("SELECT * FROM Ratings r, WHERE ro.userID =" + id.toString());
			while(rs.next()) {
				Integer ratedObjectID = rs.getInt("RatedObjectID");
				String description = rs.getString("rdescription");
				String dateCreated  = rs.getString("dateCreated");
				int numLikes = rs.getInt("numLikes");
				Double rating = rs.getDouble("numRating");
				boolean privacy = rs.getBoolean("privacy");
				Double avg = rs.getDouble("averageRating");
				Integer ID = rs.getInt("ID");
				Rating r = new Rating(ratedObjectID, description, dateCreated, numLikes, rating, privacy, ID);
				allRatings.add(r);
			}
			return allRatings;
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	
	public RatedObject getRatedObjectbyID(Integer ID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * From RatedObject WHERE ratedObjectID =" + ID);
			rs.next();
			String title = rs.getString("title");
			Double avg = rs.getDouble("averageRating");
			Integer RatedObjectID = rs.getInt("RatedObjectID");
			List<Rating> ratings= getRatingsByRatedObject(RatedObjectID.toString());
			RatedObject ro = new RatedObject(title, 0.0 , RatedObjectID, ratings);
			return ro;
			
		}
		
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public Rating getRatingbyID(Integer ID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * From Rating WHERE ratingID =" + ID);
			rs.next();
			Integer ratedObjectID = rs.getInt("RatedObjectID");
			String description = rs.getString("rdescription");
			String dateCreated  = rs.getString("dateCreated");
			int numLikes = rs.getInt("numLikes");
			Double rating = rs.getDouble("numRating");
			boolean privacy = rs.getBoolean("privacy");
			Rating r = new Rating(ratedObjectID, description, dateCreated, numLikes, rating, privacy, ID);
			return r;
			
		}
		
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	public Integer addRating(String userID, String RatedObjectID, String description, String rating,
			String privacy) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Integer row = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			Calendar c = Calendar.getInstance();
			String date = c.getTime().toString();
			
			row = st.executeUpdate("INSERT INTO Rating (userID, RatedObjectID, rdescription, dateCreated, numLikes,"
					+ "numRating, privacy) VALUES (" + Integer.parseInt(userID) + "," + Integer.parseInt(RatedObjectID) + ",'" + description + "','" + date +
					"',0," + Double.parseDouble(rating) + "," + Boolean.parseBoolean(privacy) + ");");
			rs = st.executeQuery("SELECT * FROM Rating WHERE rdescription = '" + description + "'");
			rs.next();
			row = rs.getInt("ratingID");
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(row);
		return row;
	}
	
	public Integer addRatedObject(String name, String CategoryID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Integer row = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			row = st.executeUpdate("INSERT INTO RatedObject (title, sumRatings, averageRating, totalRatings, categoryID) VALUES ('" + 
			name + "', 0.0, 0.0, 0, " + Integer.parseInt(CategoryID) + ");");
			rs = st.executeQuery("SELECT * FROM RatedObject WHERE title = '" + name + "'");
			rs.next();
			row = rs.getInt("ratedObjectID");
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}
	
	public void UpdateAverageRating(Integer ratedObjectID, Double newAverage) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			st.executeUpdate("UPDATE RatedObject SET averageRating = " + newAverage + " WHERE RatedObjectID=" + ratedObjectID);
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Rating likeRating(Integer rating_ID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/SCRater?user=root&password=root1234&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Rating WHERE ratingID = " +  rating_ID.toString());
			rs.next();
			Integer numRatings = rs.getInt("numLikes")+1;
			st.executeUpdate("UPDATE Rating SET numLikes = " + numRatings.toString() + " WHERE ratingID=" + rating_ID.toString());
			return getRatingbyID(rating_ID);
			
		}
		catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}



