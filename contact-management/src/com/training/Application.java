package com.training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.training.dao.ContactDao;
import com.training.model.Contact;
import com.training.services.ReportService;
import com.training.utils.DBConnectionUtils;


public class Application {
   // static String username="Stark@123";
   // static String password="Stark@123";
    
   /* public static boolean validateAdmin(String user,String pass)
    {
    	if(user.equals(username) && pass.equals(password))
    		return true;
    	else
    		return false;
    }*/
    public static Contact enterUserDetails()
    {    
    	
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter the name");
    	String name=sc.next();
    	System.out.println("enter the address");
    	String address=sc.next();
    	System.out.println("Enter mobile number");
    	int mobile=sc.nextInt();
    	System.out.println("Upload profile picture");
    	String image=sc.next();
    	System.out.println("Enter the date of birth (dd/MM/yyyy)");

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	  String date = sc.next();
    	  //convert String to LocalDate
    	  LocalDate localDate = LocalDate.parse(date, formatter);
    	System.out.println("Enter the email id");
    	String email=sc.next();
    	System.out.println("Enter the group "
    			+ "1.relatives"
    			+ "\n2.personal friends"
    			+ "\n3.professional friends)");
    	int ch=sc.nextInt();
    	String group="";
        if(ch==1) group="Relative";
        if(ch==2) group="Personal Friends";
        if(ch==3) group="Professional Friends";
    		
        //String group=sc.next();
        Contact contact=new Contact(name,address,mobile,image,localDate,email,group);
        return contact;
        		
    
    }
    
	public static void main(String[] args) {
		System.out.println(DBConnectionUtils.getMySqlConnection());
		Scanner sc=new Scanner(System.in);
		ContactDao dao=new ContactDao();
		ReportService service=new ReportService();
		boolean check=true;
				do {
				System.out.println("=============================================================================");
				System.out.println("CONTACT MANAGEMENT SYSTEM");
				System.out.println();
				
				System.out.println("1.Add contact"
						          + "\n2.Update contact"
						          + "\n3.Remove contact"
						          + "\n4.Display all contacts"
						          + "\n5.Add contacts from a csv file"
						          + "\n6. Explore more options");
				System.out.println("Enter your choice");
				int choice=sc.nextInt();
				//int choice=4;
				switch(choice) {
				case 1: Contact contact=enterUserDetails();
					     int rowAdded=dao.add(contact);
					     System.out.println(rowAdded+" row has been added successfully!");
					     break;
				case 2: System.out.println("Enter the phone number that you want to update");
				        int phone=sc.nextInt();
				        Contact ex=dao.findByNumber(phone);
				        System.out.println("Enter the new details");
				        Contact update=enterUserDetails();
				        int rowUpdate= dao.update(ex, update);
				        System.out.println(rowUpdate+" row has been updated successfully!");
				        break;
				case 3:System.out.println("Enter the phone number that you want to delete");
				       int phoneDel=sc.nextInt(); 
				       int rows=dao.remove(phoneDel);
				       System.out.println(rows+" row has been deleted successfully!");
				       break;
				case 4: List<Contact> contactList= dao.findAll();
				        for(Contact each:contactList)
				        {
				        	System.out.println(each);
				        }
				       
				        break;     
				case 5:System.out.println("Enter the filename (.csv)");
				       String filename=sc.next();
				       filename="input/"+filename;
				       dao.addContactFromFile(filename);
				       break;
				case 6: service.getReportService();	
				        break;
				     
				default:System.out.println("wrong entry");
				        break;
					
				}
				System.out.println("Do you want to continue? yes/no");
				String input=sc.next();
				if(input.equals("no"))
					check=false;
				}while(check);
			
			}
		
			}


