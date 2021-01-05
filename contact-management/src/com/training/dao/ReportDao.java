package com.training.dao;

import static java.util.stream.Collectors.toList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.training.ifaces.ReportAccess;
import com.training.model.Contact;

import com.training.services.FileService;
import com.training.utils.DBConnectionUtils;

public class ReportDao implements ReportAccess {
   Scanner sc;
   ContactDao dao;
   Connection con;
   FileService service;
   List<Contact> clist=null;
	
   public ReportDao() {
		super();
		sc=new Scanner(System.in);
		dao=new ContactDao();
		con=DBConnectionUtils.getMySqlConnection();
	}
	
	

	@Override
	public void getBirthday(int month) {
		if(month>=1 && month<=12)
		{
		System.out.println("How do you want to print the list?"
				+ "\n1.Generate to console"
				+ "\n2.Save it to a file");
		int choice=sc.nextInt();
		boolean check=true;
		clist=dao.findAll();
		if(choice==1)
		{
			for(Contact eachContact:clist) {
				if(eachContact.getDob().getMonthValue()==month) {
					System.out.println(eachContact);
					check=false;
					}
			}
		}
		  if(choice==2)
		   {
			System.out.println("Enter your file name in .csv format");
			String filename=sc.next();
			filename="output/"+filename;
			service=new FileService(filename);
             String content="";
			for(Contact eachContact:clist) {
				if(eachContact.getDob().getMonthValue()==month) {
					content+=eachContact.toString();
					check=false;
			 } 
			}
		     service.addToFile(content);
	       }
		  if(check) System.out.println("No data found for the month "+month);
		}
		else {
			System.out.println("Invalid month entry");
		}
     }

    @Override
	public void getPerticularGroup(String group) {
    	System.out.println("How do you want to print the list?"
				+ "\n1.Generate to console"
				+ "\n2.Save it to a file");
		int choice=sc.nextInt();
		boolean check=true;
		clist=dao.findAll();
		if(choice==1)
		{
			for(Contact eachContact:clist) {
				if(eachContact.getGroup().equals(group)) {
					System.out.println(eachContact);
					check=false;
				}
			}
		}
		  if(choice==2)
		   {
			System.out.println("Enter your file name in .csv format");
			String filename=sc.next();
			filename="output/"+filename;
			
			service=new FileService(filename);
            String content="";
			for(Contact eachContact:clist) {
				if(eachContact.getGroup().equals(group)) {
					content+=eachContact.toString();
					check=false;
			 } 
			}
		
		     service.addToFile(content);
	       }
		  if(check) System.out.println("No data found for group "+group);
		}



	@Override
	public void nameAndEmail() {
		System.out.println("How do you want to print the list?"
				+ "\n1.Generate to console"
				+ "\n2.Save it to a file");
		int choice=sc.nextInt();
		clist=dao.findAll();
		List<Contact> sortedList=clist.stream()
		                         .sorted(Comparator.comparing(Contact::getName))
		                         .collect(toList());
		if(choice==1)
		{
			for(Contact eachContact:sortedList) {
				
			System.out.println(eachContact.getName()+","+eachContact.getEmail());}
		}
		  if(choice==2)
		   {
			System.out.println("Enter your file name in .csv format");
			String filename=sc.next();
			filename="output/"+filename;
			service=new FileService(filename);
             String content="";
			for(Contact eachContact:sortedList) {
				
					content+="\n"+eachContact.getName()+","+eachContact.getEmail();
			 } 
		
		     service.addToFile(content);
	       }
		
	}



	@Override
	public void nameAndPhone() {
		
		System.out.println("How do you want to print the list?"
				+ "\n1.Generate to console"
				+ "\n2.Save it to a file");
		int choice=sc.nextInt();
		clist=dao.findAll();
		List<Contact> sortedList=clist.stream()
		                         .sorted(Comparator.comparing(Contact::getName))
		                         .collect(toList());
		if(choice==1)
		{
			for(Contact eachContact:sortedList) {
				
			System.out.println(eachContact.getName()+","+eachContact.getMobileNum());}
		}
		  if(choice==2)
		   {
			System.out.println("Enter your file name in .csv format");
			String filename=sc.next();
			filename="output/"+filename;
			service=new FileService(filename);
             String content="";
			for(Contact eachContact:sortedList) {
				
					content+="\n"+eachContact.getName()+","+eachContact.getMobileNum();
			 } 
		
		     service.addToFile(content);
	       }
	}
	@Override
	public void countByGroup() {

		String sql="select count(name) as count,groupName"
				+ "  from contacts"
				+ " group by groupName order by count(name);";
		
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			
			ResultSet rs=pstmt.executeQuery();
			System.out.println("How do you want to print the list?"
					+ "\n1.Generate to console"
					+ "\n2.Save it to a file");
			int choice=sc.nextInt();
			String file=null;
			if(choice==2)
			{
				System.out.println("Enter your file name in .csv format");
				file=sc.next();
				file="output/"+file;
			}
			
			while(rs.next()) {
				int count=rs.getInt("count");
				String group=rs.getString("groupName");
				if(group.equals("Relative")) {
					System.out.println("\nRelatives:"+count);
					printGroup("Relative",choice,file);
					}
				else if(group.equals("Personal Friends")) {
					System.out.println("\nPersonal Friends:"+count);
					printGroup("Personal Friends",choice,file);
					}
				else {
					System.out.println("\nProfessional friends:"+count);
					printGroup("Professional Friends",choice,file);
					}
			
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void printGroup(String group,int choice,String file) {
		
		boolean check=true;
		clist=dao.findAll();
		if(choice==1)
		{   //System.out.println("Count of "+group+":"+count);
			for(Contact eachContact:clist) {
				if(eachContact.getGroup().equals(group)) {
					System.out.println(eachContact.getName()+":"+eachContact.getMobileNum());
					check=false;
				}
			}
		}
		  if(choice==2)
		   {
			/*System.out.println("Enter your file name in .csv format");
			String filename=sc.next();
			filename="output/"+filename;*/
			
			service=new FileService(file);
            String content="";
			for(Contact eachContact:clist) {
				if(eachContact.getGroup().equals(group)) {
					content+=eachContact.getName()+":"+eachContact.getMobileNum();
					check=false;
			 } 
			}
		
		     service.addToFile(content);
	       }
		//  if(check) System.out.println("No data found for group "+group);
	}
}
		
	

