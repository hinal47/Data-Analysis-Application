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

public class populateBusinessTable {
	
	public void populateBusiness() throws SQLException{
		
		Connection conDB = null;
		PreparedStatement psBusiness = null;
		PreparedStatement psBusinessHours = null;
		PreparedStatement psMainCategory = null;
		PreparedStatement psSubCategory = null;
		PreparedStatement psNeighbors = null;
		PreparedStatement psAttributes = null;
		
		String qBusiness = "INSERT INTO BUSINESS (BUSINESS_ID, FULL_ADDRESS, OPEN, CITY, REVIEWS_COUNT, BUSINESS_NAME, LONGITUDE, STATE, STARS, LATITUDE, BUSINESS_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String qBusinessHours = "INSERT INTO BUSINESS_HOURS (DAY_OF_WEEK, FROM_HOUR, TO_HOUR, BUSINESS_ID) VALUES (?,?,?,?)";
		String qMainCategory = "INSERT INTO MAIN_CATEGORIES (CATEGORY_NAME, BUSINESS_ID) VALUES (?,?)";
		String qSubCategory = "INSERT INTO SUB_CATEGORIES (CATEGORY_NAME, BUSINESS_ID) VALUES (?,?)";
		String qNeighbors = "INSERT INTO NEIGHBORHOODS (NEIGHBOR_NAME, BUSINESS_ID) VALUES (?,?)";
		String qAttributes ="INSERT INTO BUSINESS_ATTRIBUTES (ATTRIBUTE_NAME, BUSINESS_ID) VALUES (?,?)";
		
		JSONParser jParser = new JSONParser();
		
		try {
			conDB = getDBConnection();
			psBusiness = conDB.prepareStatement(qBusiness);
			psBusinessHours = conDB.prepareStatement(qBusinessHours);
			psMainCategory = conDB.prepareStatement(qMainCategory);
			psSubCategory = conDB.prepareStatement(qSubCategory);
			psNeighbors = conDB.prepareStatement(qNeighbors);
			psAttributes = conDB.prepareStatement(qAttributes);
			
			FileReader fReader = new FileReader("D:/Hinal/Spring 2018/COEN 280 Database Systems/Assignments/Assignment 3/YelpDataset/YelpDataset/yelp_business.json");
			BufferedReader bfReader = new BufferedReader(fReader);
			String next;
			
			while((next = bfReader.readLine()) != null) {
				
				Object objParse = jParser.parse(next);
				JSONObject objJson = (JSONObject) objParse;
				
				psBusiness.setString(1, (String) objJson.get("business_id"));
				psBusiness.setString(2, (String) objJson.get("full_address"));
				
				if((Boolean) objJson.get("open")) { psBusiness.setInt(3, 1); }
				else { psBusiness.setInt(3, 0); }
				
				psBusiness.setString(4, (String) objJson.get("city"));
				psBusiness.setInt(5, ((Long) objJson.get("review_count")).intValue());
				psBusiness.setString(6, (String) objJson.get("name"));
				psBusiness.setFloat(7, ((Double) objJson.get("longitude")).floatValue());
				psBusiness.setString(8, (String) objJson.get("state"));
				psBusiness.setFloat(9, ((Double) objJson.get("stars")).floatValue());
				psBusiness.setFloat(10, ((Double) objJson.get("latitude")).floatValue());
				psBusiness.setString(11, (String) objJson.get("type"));
				
				psBusiness.executeUpdate();
				
				if(objJson.get("neighborhoods") != null)
				{
					JSONArray arrNeighbors = (JSONArray) objJson.get("neighborhoods");
					Iterator<String> itNeighbors = arrNeighbors.iterator();
								
					while(itNeighbors.hasNext())
					{
						psNeighbors.setString(1, itNeighbors.next());
						psNeighbors.setString(2, (String) objJson.get("business_id"));
						psNeighbors.executeUpdate();
					}
				}
				
				JSONArray arrCategories = (JSONArray) objJson.get("categories");
				Iterator<String> itCategories = arrCategories.iterator();
				String strCategories;
				
				while(itCategories.hasNext())
				{
					strCategories = itCategories.next();
					if(strCategories.equals("Active Life") || strCategories.equals("Arts & Entertainment") || strCategories.equals("Automotive") || 
							strCategories.equals("Car Rental") || strCategories.equals("Cafes") || strCategories.equals("Beauty & Spas") || 
							strCategories.equals("Convenience Stores") || strCategories.equals("Dentists") || strCategories.equals("Doctors") ||
							strCategories.equals("Drugstores") || strCategories.equals("Department Stores") || strCategories.equals("Education") ||
							strCategories.equals("Event Planning & Services") || strCategories.equals("Flowers & Gifts") || 
							strCategories.equals("Food") || strCategories.equals("Health & Medical") || strCategories.equals("Home Services") ||
							strCategories.equals("Home & Garden") || strCategories.equals("Hospitals") || strCategories.equals("Hotels & Travel") ||
							strCategories.equals("Hardware Stores") || strCategories.equals("Grocery") || strCategories.equals("Medical Centers") ||
							strCategories.equals("Nurseries & Gardening") || strCategories.equals("Nightlife") || strCategories.equals("Restaurants") ||
							strCategories.equals("Shopping") || strCategories.equals("Transportation"))
					{
						psMainCategory.setString(1, strCategories);
						psMainCategory.setString(2, (String) objJson.get("business_id"));
						psMainCategory.executeUpdate();
					}
					else
					{
						psSubCategory.setString(1, strCategories);
						psSubCategory.setString(2, (String) objJson.get("business_id"));
						psSubCategory.executeUpdate();
					}
					
				}
				
				if(objJson.get("attributes") != null)
				{
					JSONObject objAttributes = (JSONObject) objJson.get("attributes");
					for (Object objKey : objAttributes.keySet()) 
					{
						String strKey = (String)objKey;
				        Object objKeyValue = objAttributes.get(strKey);				      

				        if (objKeyValue instanceof JSONObject)
				        {
				        	JSONObject objAttributes_1 = (JSONObject) objAttributes.get(objKey);
				        	for (Object objKey_1 : objAttributes_1.keySet())
				        	{
				        		String strKey_1 = (String)objKey_1;
				        		Object objKeyValue_1 = objAttributes_1.get(strKey_1);
				        		if (objKeyValue_1 instanceof Integer)
				        		{
				        			String strAttributeValue = ((Long) objAttributes_1.get(strKey_1)).toString();
				        			strKey_1 = strKey_1 + "_" + strAttributeValue;
									psAttributes.setString(1, strKey_1);
									psAttributes.setString(2, (String) objJson.get("business_id"));
									psAttributes.executeUpdate();
				        		}
				        		else if (objKeyValue_1 instanceof String)
				        		{
				        			String strAttributeValue = (String) objAttributes_1.get(strKey_1);
				        			strKey_1 = strKey_1 + "_" + strAttributeValue;
									psAttributes.setString(1, strKey_1);
									psAttributes.setString(2, (String) objJson.get("business_id"));
									psAttributes.executeUpdate();
				        		}
				        		else if (objKeyValue_1 instanceof Boolean)
				        		{
				        			boolean a = (Boolean) objAttributes_1.get(strKey_1);
				        			String strAttributeValue = String.valueOf(a);
				        			strKey_1 = strKey_1 + "_" + strAttributeValue;
									psAttributes.setString(1, strKey_1);
									psAttributes.setString(2, (String) objJson.get("business_id"));
									psAttributes.executeUpdate();
				        		}
				        	}
				        }
				        else
				        {
				        	if (objKeyValue instanceof Integer)
			        		{
			        			String strAttributeValue = ((Long) objAttributes.get(strKey)).toString();
			        			strKey = strKey + "_" + strAttributeValue;
								psAttributes.setString(1, strKey);
								psAttributes.setString(2, (String) objJson.get("business_id"));
								psAttributes.executeUpdate();
			        		}
			        		else if (objKeyValue instanceof String)
			        		{
			        			String strAttributeValue = (String) objAttributes.get(strKey);
			        			strKey = strKey + "_" + strAttributeValue;
								psAttributes.setString(1, strKey);
								psAttributes.setString(2, (String) objJson.get("business_id"));
								psAttributes.executeUpdate();
			        		}
			        		else if (objKeyValue instanceof Boolean)
			        		{
			        			boolean a = (Boolean) objAttributes.get(strKey);
			        			String strAttributeValue = String.valueOf(a);
			        			strKey = strKey + "_" + strAttributeValue;
								psAttributes.setString(1, strKey);
								psAttributes.setString(2, (String) objJson.get("business_id"));
								psAttributes.executeUpdate();
			        		} 
				        }
				        
				    }
				}
				
				//
				
				if(objJson.get("hours") != null)
				{
					JSONObject objHours = (JSONObject) objJson.get("hours");
					for (Object objKey : objHours.keySet()) 
					{
				        String strKey = (String)objKey;				        
				        JSONObject objHours_1 = (JSONObject) objHours.get(strKey);
				        String strOpenHour = (String) objHours_1.get("open");
				        Float fltOpenHour = funcCastHour(strOpenHour);
				        String strCloseHour = (String) objHours_1.get("close");
				        Float fltCloseHour = funcCastHour(strCloseHour);
				        psBusinessHours.setString(1, strKey);
				        psBusinessHours.setFloat(2, fltOpenHour);
				        psBusinessHours.setFloat(3, fltCloseHour);
				        psBusinessHours.setString(4, (String) objJson.get("business_id"));
				        psBusinessHours.executeUpdate();				       				    
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
			
			if(psBusiness != null) { psBusiness.close();}
			if(psBusinessHours != null) { psBusinessHours.close();}
			if(psMainCategory != null) { psMainCategory.close();}
			if(psSubCategory != null) { psSubCategory.close();}
			if(psNeighbors != null) {psNeighbors.close();}
			if(psAttributes != null) {psAttributes.close();}
			if(conDB != null) {conDB.close();}
		}
		
	}
	
	public static Float funcCastHour(String strHour)
	{
		String[] arrHour = strHour.split(":");
		float fltHour = Float.parseFloat(arrHour[0]);
		float fltMin = Float.parseFloat(arrHour[1]);
		fltMin = fltMin / 100;
		fltHour = fltHour + fltMin;
		return fltHour;
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
		System.out.println("POPULATING BUSINESS NOW!!!");
		populateBusinessTable obj = new populateBusinessTable();
		obj.populateBusiness();
	}

}
