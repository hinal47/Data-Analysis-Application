import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class populateReviewsTable {
	
	public void populateReviews() throws SQLException {
		
		Connection conDB = null;
		PreparedStatement psReviews = null;
		String qReviews = "INSERT INTO REVIEWS (REVIEWS_ID, USER_ID, BUSINESS_ID, VOTES_FUNNY, VOTES_USEFUL, VOTES_COOL, STARS, REVIEW_DATE, REVIEW_TEXT, REVIEW_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		JSONParser jParser = new JSONParser();
		
		try {
			
			conDB = getDBConnection();
			psReviews = conDB.prepareStatement(qReviews);
			
			FileReader fReader = new FileReader("D:/Hinal/Spring 2018/COEN 280 Database Systems/Assignments/Assignment 3/YelpDataset/YelpDataset/yelp_review.json");
			BufferedReader bfReader = new BufferedReader(fReader);
			String next;
			
			while ((next = bfReader.readLine()) != null) 
			{
				Object objParse = jParser.parse(next);
				JSONObject objJson = (JSONObject) objParse;
				
				psReviews.setString(1, (String) objJson.get("review_id"));
				psReviews.setString(2, (String) objJson.get("user_id"));
				psReviews.setString(3, (String) objJson.get("business_id"));
				
				JSONObject objVotes = (JSONObject) objJson.get("votes");

				psReviews.setInt(4, ((Long) objVotes.get("funny")).intValue());
				psReviews.setInt(5, ((Long) objVotes.get("useful")).intValue());
				psReviews.setInt(6, ((Long) objVotes.get("cool")).intValue());

				psReviews.setInt(7, ((Long) objJson.get("stars")).intValue());
				psReviews.setString(8, (String) objJson.get("date"));
				psReviews.setString(9, (String) objJson.get("text"));
				psReviews.setString(10, (String) objJson.get("type"));

				psReviews.executeUpdate();	
			}
			
			fReader.close();
			
		}catch(FileNotFoundException Ex) {
			Ex.printStackTrace();
		}catch(IOException Ex) {
			Ex.printStackTrace();
		}catch(ParseException Ex) {
			Ex.printStackTrace();
		}finally {
			
			if(psReviews != null) {psReviews.close();}
			if(conDB != null) {conDB.close();}
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
		System.out.println("POPULATING REVIEWS NOW!!!");
		populateReviewsTable obj = new populateReviewsTable();
		obj.populateReviews();
	}
}
