import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class populateCheckinTable {
	
	public void populateCheckin() throws SQLException {
		
		Connection conDB = null;
		PreparedStatement psCheckIn = null;
		String qCheckIn = "INSERT INTO CHECKIN (CHECKIN_DAY, CHECKIN_HOUR, CHECKIN_COUNT, BUSINESS_ID) VALUES (?,?,?,?)";
		
		JSONParser jParser = new JSONParser();
		
		try {
			conDB = getDBConnection();
			psCheckIn = conDB.prepareStatement(qCheckIn);
	
			FileReader fReader = new FileReader("D:/Hinal/Spring 2018/COEN 280 Database Systems/Assignments/Assignment 3/YelpDataset/YelpDataset/yelp_checkin.json");
			BufferedReader bfReader = new BufferedReader(fReader);
			String next;
			
			while((next = bfReader.readLine()) != null) {
				
				Object objParse = jParser.parse(next);
				JSONObject objJson = (JSONObject) objParse;
				
				psCheckIn.setString(4, (String) objJson.get("business_id"));
				JSONObject objJson_1 = (JSONObject) objJson.get("checkin_info");
				String strDay;
				int intHour;
				int intCount;
				
				for (Object objKey : objJson_1.keySet()) 
				{
			        String strKey = (String)objKey;
			        Object objKeyValue = objJson_1.get(strKey);
			        strDay = funcParseDay(strKey);
			        intHour = funcParseHour(strKey);
			        intCount = ((Long) objKeyValue).intValue();
			        
			        psCheckIn.setString(1, strDay);
			        psCheckIn.setInt(2, intHour);
			        psCheckIn.setInt(3, intCount);
			        psCheckIn.executeUpdate();
			    }
			}
			
			fReader.close();
			
		}catch(FileNotFoundException Ex) {
			Ex.printStackTrace();
		}catch(IOException Ex) {
			Ex.printStackTrace();
		}catch(ParseException Ex) {
			Ex.printStackTrace();
		}finally {
			
			if(psCheckIn != null) {psCheckIn.close();}
			if(conDB != null) {conDB.close();}
		}
	}
	
	public static String funcParseDay(String strDay)
	{
		String[] arrDay = strDay.split("-");
		String strDay_1 = "Incorrect Value!!!";
		
		if (arrDay[1].equals("0"))
		{
			strDay_1 = "SUNDAY";
		}
		if (arrDay[1].equals("1"))
		{
			strDay_1 = "MONDAY";
		}
		if (arrDay[1].equals("2"))
		{
			strDay_1 = "TUESDAY";
		}
		if (arrDay[1].equals("3"))
		{
			strDay_1 = "WEDNESDAY";
		}
		if (arrDay[1].equals("4"))
		{
			strDay_1 = "THURSDAY";
		}
		if (arrDay[1].equals("5"))
		{
			strDay_1 = "FRIDAY";
		}
		if (arrDay[1].equals("6"))
		{
			strDay_1 = "SATURDAY";
		}
		
		return strDay_1;
	}
	
	public static int funcParseHour(String strHour)
	{
		String[] arrHour = strHour.split("-");
		return Integer.parseInt(arrHour[0]);
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
		System.out.println("POPULATING CHECKIN NOW!!!");
		populateCheckinTable obj = new populateCheckinTable();
		obj.populateCheckin();
	}
}
