package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getDBConnection() {
		
		Connection conDB = null;
		
		try {
			
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			
			String strHost = "localhost"; 
			String strPort = "1521"; 
			String strDBName = "HINAL"; 
			String strUserName = "Yelp_Database"; 
			String strPassword = "hinal"; 
			String strDBUrl = "jdbc:oracle:thin:@" + strHost + ":" + strPort + ":" + strDBName; 
			
			conDB = DriverManager.getConnection(strDBUrl, strUserName, strPassword); 
			
			if(conDB != null)
			{
				System.out.println("Connected to Database Successfully!");
			}
			
			return conDB;
			
		}catch(SQLException Ex) {
			
			System.out.println(Ex.getMessage());
		}
		
		return conDB;
	}
}
