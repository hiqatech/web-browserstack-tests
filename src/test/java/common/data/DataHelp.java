package common.data;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 

public class DataHelp {
	
	static String selectSQLQueryResult = null;
	
	 public static String selectSQLQuery(String url,String passw, String query, String resultKey) { 
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	            Connection con = DriverManager.getConnection("jdbc:mysql:"  + url, "root", passw); 
	
            	Statement statement = con.createStatement(); 
            	ResultSet resultSet = statement.executeQuery(query); 
            	selectSQLQueryResult = resultSet.getString(resultKey);
            	return "PASS";
	        } catch (Exception ex) { 
	        	return ex.getMessage(); }
	  } 
	 
	 public static String setSQLQuery(String url,String passw, String query, String resultKey) { 
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	            Connection con = DriverManager.getConnection("jdbc:mysql:"  + url, "root", passw); 
	
         	Statement statement = con.createStatement(); 
         	ResultSet resultSet = statement.executeQuery(query); 
         	selectSQLQueryResult = resultSet.getString(resultKey);
        	return "PASS";
	        } catch (Exception ex) { 
	        	return ex.getMessage(); }
	  } 
	
}
