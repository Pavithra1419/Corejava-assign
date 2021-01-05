package com.training.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DBConnectionUtils {
	
	
	
    public static Connection getMySqlConnection()
    {
    	Connection con=null;
    	String filename="DBConnection.properties";
    	
    	try {
    		InputStream inStream=DBConnectionUtils.class.getClassLoader().getResourceAsStream(filename);
    		
    		Properties props=new Properties();
    		props.load(inStream);
    		con=DriverManager.getConnection(
    				props.getProperty("datasource.url"),
    				props.getProperty("datasource.username"),
    				props.getProperty("datasource.password"));
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return con;
    }
    
    public static RowSet getCachedRowSet()
    {
    	CachedRowSet rowset=null;
    	RowSetFactory fact=null;
    	
    	try {
    		fact=RowSetProvider.newFactory();
    		rowset=fact.createCachedRowSet();
    		
    		String filename="DBConnection.properties";
           InputStream inStream=DBConnectionUtils.class.getClassLoader().getResourceAsStream(filename);
    		
    		Properties props=new Properties();
    		props.load(inStream);
    		rowset.setUrl(props.getProperty("datasource.url"));
    		rowset.setUsername(props.getProperty("datasource.username"));
    		rowset.setPassword(props.getProperty("datasource.password"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return rowset;
    }
}
