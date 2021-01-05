package com.training.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DB {
	

    public static Connection getConnection()  {
    	
    	Connection con=null;
    	String filename="database.properties";
        try{
        	InputStream inStream=DB.class.getClassLoader().getResourceAsStream(filename);
            Properties properties=new Properties();
            properties.load(inStream);
            con=DriverManager.getConnection(
    				properties.getProperty("DB_URL"),
    				properties.getProperty("DB_USERNAME"),
    				properties.getProperty("DB_PASSWORD"));
           
        }
        catch(SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return con;
    }
}
