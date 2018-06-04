
import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class populateUserTable {

	public void populateUser() throws SQLException {
		
		
		Connection conDB = null;
		PreparedStatement preparedStatementUser = null;
		PreparedStatement preparedStatementFriends = null;
		PreparedStatement preparedStatementElite = null;
		String queryUser = "INSERT INTO YELP_USER (YELPING_SINCE, VOTES_FUNNY, VOTES_USEFUL, VOTES_COOL, REVIEWS_COUNT, USERNAME, USER_ID, FANS, AVERAGE_STARS, USER_TYPE, COMPLIMENTS_FUNNY, COMPLIMENTS_COOL, COMPLIMENTS_WRITER, COMPLIMENTS_PHOTOS, COMPLIMENTS_HOT, COMPLIMENTS_MORE, COMPLIMENTS_PLAIN, COMPLIMENTS_NOTE, COMPLIMENTS_PROFILE, COMPLIMENTS_CUTE, COMPLIMENTS_LIST) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String queryFriends = "INSERT INTO FRIENDS (USER_ID, FRIEND_ID) VALUES (?,?)";
		String queryElite = "INSERT INTO ELITE (USER_ID, ELITE) VALUES (?,?)";
		
		JSONParser jParser = new JSONParser();
		
		try {
			
			conDB = getDBConnection();
			preparedStatementUser = conDB.prepareStatement(queryUser);
			preparedStatementFriends = conDB.prepareStatement(queryFriends);
			preparedStatementElite = conDB.prepareStatement(queryElite);
			FileReader fReader = new FileReader("D:/Hinal/Spring 2018/COEN 280 Database Systems/Assignments/Assignment 3/YelpDataset/YelpDataset/yelp_user.json");
			BufferedReader bfReader = new BufferedReader(fReader);
			String next;
			
			while((next = bfReader.readLine()) != null) {
				
				Object objParse = jParser.parse(next);
				JSONObject objJson = (JSONObject) objParse;
				JSONObject objVotes = (JSONObject) objJson.get("votes");
				
				preparedStatementUser.setString(1, (String) objJson.get("yelping_since"));
				
				preparedStatementUser.setInt(2, ((Long) objVotes.get("funny")).intValue());
				preparedStatementUser.setInt(3, ((Long) objVotes.get("useful")).intValue());
				preparedStatementUser.setInt(4, ((Long) objVotes.get("cool")).intValue());
				
				preparedStatementUser.setInt(5, ((Long) objJson.get("review_count")).intValue());
				preparedStatementUser.setString(6, (String) objJson.get("name"));
				preparedStatementUser.setString(7, (String) objJson.get("user_id"));
				preparedStatementUser.setInt(8, ((Long) objJson.get("fans")).intValue());
				preparedStatementUser.setFloat(9,  ((Double) objJson.get("average_stars")).floatValue());
				preparedStatementUser.setString(10, (String) objJson.get("type"));
				
				JSONObject objCompliments = (JSONObject) objJson.get("compliments");
				
				preparedStatementUser.setInt(11, 0);
				if(objCompliments.get("funny") != null) {preparedStatementUser.setInt(11, ((Long) objCompliments.get("funny")).intValue());};
				
				preparedStatementUser.setInt(12, 0);
				if(objCompliments.get("cool") != null) {preparedStatementUser.setInt(12, ((Long) objCompliments.get("cool")).intValue());};
				
				preparedStatementUser.setInt(13, 0);
				if(objCompliments.get("writer") != null) {preparedStatementUser.setInt(13, ((Long) objCompliments.get("writer")).intValue());};
				
				preparedStatementUser.setInt(14, 0);
				if(objCompliments.get("photos") != null) {preparedStatementUser.setInt(14, ((Long) objCompliments.get("photos")).intValue());};
				
				preparedStatementUser.setInt(15, 0);
				if(objCompliments.get("hot") != null) {preparedStatementUser.setInt(15, ((Long) objCompliments.get("hot")).intValue());};
				
				preparedStatementUser.setInt(16, 0);
				if(objCompliments.get("more") != null) {preparedStatementUser.setInt(16, ((Long) objCompliments.get("more")).intValue());};
				
				preparedStatementUser.setInt(17, 0);
				if(objCompliments.get("plain") != null) {preparedStatementUser.setInt(17, ((Long) objCompliments.get("plain")).intValue());};
				
				preparedStatementUser.setInt(18, 0);
				if(objCompliments.get("note") != null) {preparedStatementUser.setInt(18, ((Long) objCompliments.get("note")).intValue());};
				
				preparedStatementUser.setInt(19, 0);
				if(objCompliments.get("profile") != null) {preparedStatementUser.setInt(19, ((Long) objCompliments.get("profile")).intValue());};
				
				preparedStatementUser.setInt(20, 0);
				if(objCompliments.get("cute") != null) {preparedStatementUser.setInt(20, ((Long) objCompliments.get("cute")).intValue());};
				
				preparedStatementUser.setInt(21, 0);
				if(objCompliments.get("list") != null) {preparedStatementUser.setInt(21, ((Long) objCompliments.get("list")).intValue());};
				
				preparedStatementUser.executeUpdate();
				
				if(objJson.get("friends") != null) {
					
					JSONArray arrFriends = (JSONArray) objJson.get("friends");
					Iterator<String> itFriends = arrFriends.iterator();
					
					while(itFriends.hasNext()) {
						
						preparedStatementFriends.setString(1, (String) objJson.get("user_id"));
						preparedStatementFriends.setString(2, itFriends.next());
						preparedStatementFriends.executeUpdate();
					}
				}
				
				if(objJson.get("elite") != null) {
					
					JSONArray arrElite = (JSONArray) objJson.get("elite");
					Iterator<Long> itElite = arrElite.iterator();
					
					while(itElite.hasNext()){
						
						preparedStatementElite.setString(1, (String) objJson.get("user_id"));
						preparedStatementElite.setInt(2, (itElite.next()).intValue());
						preparedStatementElite.executeUpdate();
					}
					
				}
				
			}
			
			fReader.close();
		
		}catch(FileNotFoundException Ex) {
			Ex.printStackTrace();
		}catch(IOException Ex) {
			Ex.printStackTrace();
		}catch(ParseException Ex) {
			Ex.printStackTrace();
		}catch(Exception Ex) {
			Ex.printStackTrace();
		}finally {
			
			if(preparedStatementUser != null) preparedStatementUser.close();
			if(preparedStatementFriends != null) preparedStatementFriends.close();
			if(preparedStatementElite != null) preparedStatementElite.close();
			if(conDB != null) conDB.close();
		}
	}
	
	
	
	public static Connection getDBConnection() {
		
		Connection conDB = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(ClassNotFoundException Ex) {
			
			System.out.println(Ex.getMessage());
		}
		
		try {
			
			conDB = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:HINAL", "Yelp_Database", "hinal");
			return conDB;
			
		}catch(SQLException Ex) {
			
			System.out.println(Ex.getMessage());
		}
		
		return conDB;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println("POPULATING USER NOW!!!");
		populateUserTable obj = new populateUserTable();
		obj.populateUser();
	}
}
