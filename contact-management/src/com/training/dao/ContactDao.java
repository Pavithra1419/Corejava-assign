package com.training.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


import com.training.ifaces.DataAccess;
import com.training.model.Contact;
import com.training.utils.DBConnectionUtils;

public class ContactDao implements DataAccess<Contact> {
    private Connection con;
    
    public ContactDao()
    {
    	super();
    	con=DBConnectionUtils.getMySqlConnection();
    }
    public ContactDao(Connection con) {
    	super();
    	this.con=con;
    }
    public Contact resultSet(ResultSet rs)
    {


    	Contact contact=null;
		try {
			while(rs.next()) {
			String name = rs.getString(1);
			String address=rs.getString(2);
			int phone=rs.getInt(3);
			String image=rs.getString(4);
			LocalDate dob=rs.getDate(5).toLocalDate();
			String email=rs.getString(6);
			String group=rs.getString(7);
			
				contact=new Contact(name,address,phone,image,dob,email,group);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contact;
		
    }
    
	@Override
	public int add(Contact t) {
		String sql="insert into contacts values(?,?,?,?,?,?,?)";
		int rowsAdded=0;
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getAddress());
			pstmt.setInt(3, t.getMobileNum());
			pstmt.setString(4, t.getProfile());
			pstmt.setDate(5, Date.valueOf(t.getDob()));
			pstmt.setString(6, t.getEmail());
			pstmt.setString(7, t.getGroup());
			
		rowsAdded=pstmt.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rowsAdded;
	}

	@Override
	public int update(Contact ex, Contact update) {
		String sql="update contacts set name=?, address=?,mobileNum=?,image=?,dob=?,email=?,groupName=? where mobileNum=?";
		int rowsUpdated=0;
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, update.getName());
			pstmt.setString(2, update.getAddress());
			pstmt.setInt(3, update.getMobileNum());
			pstmt.setString(4, update.getProfile());
			pstmt.setDate(5, Date.valueOf(update.getDob()));
			pstmt.setString(6, update.getEmail());
			pstmt.setString(7, update.getGroup());
			pstmt.setInt(8, ex.getMobileNum());
			
			rowsUpdated=pstmt.executeUpdate();
			
			
		
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	@Override
	public int remove(int mobileNum) {
		String sql="delete from contacts where mobileNum=?";
		int rowsDeleted=0;
		//Contact contact=findByNumber(mobileNum);
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setInt(1, mobileNum);
			
			
		rowsDeleted=pstmt.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
          return rowsDeleted;
	}
	
public Contact findByNumber(int phoneNum) {
		
		String sql="select * from contacts where mobileNum=?";
		Contact contact=null;
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setLong(1,phoneNum);
			
			
			ResultSet rsFind=pstmt.executeQuery();	
			contact=resultSet(rsFind);
			if(rsFind.next())
			{
				String name=rsFind.getString(1);
				String address=rsFind.getString(2);
				int phone=rsFind.getInt(3);
				String image=rsFind.getString(4);
				LocalDate dob=rsFind.getDate(5).toLocalDate();
				String email=rsFind.getString(6);
				String group=rsFind.getString(7);
				
				contact=new Contact(name,address,phone,image,dob,email,group);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	
		
		return contact;
	}
public List<Contact> findAll() {
	// TODO Auto-generated method stub
	String sql="select * from  contacts";
	List<Contact> contactList=new ArrayList<Contact>();
	try(PreparedStatement pstmt=con.prepareStatement(sql)){
		
		
	ResultSet rs=pstmt.executeQuery();
	
     
	
	while(rs.next())
	{
		String name = rs.getString(1);
		String address=rs.getString(2);
		int phone=rs.getInt(3);
		String image=rs.getString(4);
		LocalDate dob=rs.getDate(5).toLocalDate();
		String email=rs.getString(6);
		String group=rs.getString(7);
		
	    Contact contact=new Contact(name,address,phone,image,dob,email,group);
		contactList.add(contact);
	}
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	return contactList;
	
	
}
@Override
public void addContactFromFile(String filename) {
	String line;  
    String delimiter = ",";  
    try   
    {  

        String[] contactArray;
        File file=new File(filename);
        FileReader fileReader=new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);  
        while ((line = reader.readLine()) != null)
        {  
            contactArray = line.split(delimiter);
            
            String name=contactArray[0];
            name = name.replaceAll("\"","");
            String address=contactArray[1];
            int mobileNum=Integer.parseInt(contactArray[2]);
            String profileRef=contactArray[3];
            LocalDate dateLocal=null;
            
            String dob=contactArray[4];
            String[] dateArray=dob.split("/");
            
            int date=Integer.parseInt(dateArray[0]);
            int month=Integer.parseInt(dateArray[1]);
            int year=Integer.parseInt(dateArray[2]);
            
            dateLocal = LocalDate.of(year, Month.of(month), date);
            
            String email=contactArray[5];
            String group=contactArray[6];
            group = group.replaceAll("\"","");
            Contact contact=null;
            boolean check=true;
            try{
                contact=new Contact(name, address, mobileNum, profileRef, dateLocal, email, group);
            }catch(ClassCastException | InputMismatchException  e) {
                System.out.println("Your input in csv file is invalid, please try again");
                check=false;
            }
            if(check==true) {
                System.out.println("Rows updated is:" + add(contact));
            }
            
        }  
        
        reader.close();
    }   
    catch (IOException e)   
    {  
        e.printStackTrace();  
    }
}
	

}
/*
@Override
public List<Contact> getNameAndEmail() {
	String sql="select name,email from  contacts "
			+ "order by name";
	List<Contact> contactList=new ArrayList<Contact>();
	try(PreparedStatement pstmt=con.prepareStatement(sql)){
		
		
	ResultSet rs=pstmt.executeQuery();
	

	
	while(rs.next())
	{
		String name=rs.getString("name");
		
		String email=rs.getString("email");
	
		
		Contact contact=new Contact(name,address,phone,image,dob,email,group);
		contactList.add(contact);
	}
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	return null;
}
*/




