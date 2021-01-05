package com.training.services;

import java.util.Scanner;

import com.training.dao.ContactDao;
import com.training.dao.ReportDao;

public class ReportService {
	
	ContactDao dao=null;
	Scanner sc;
	ReportDao rdao=null;
	public ReportService()
	{
		super();
		dao=new ContactDao();
		rdao=new ReportDao();
		sc=new Scanner(System.in);
		
	}
	
	public void getReportService()
	{
		boolean check=true;
		do {
		System.out.println("Here are your options");
		System.out.println("1.Birthdays in a given month"
				+ "\n2.Display the list of Contact by Group "
				+ "\n3.List of Contacts with their name and email Id"
				+ "\n4.List of Contact with their name and mobile Number"
				+ "\n5.Contacts in each group"
				+ "\n6.Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		    case 1: System.out.println("Enter the month(between 1-12)");
		            int month=sc.nextInt();
		            rdao.getBirthday(month);
		            break;
		    case 2: System.out.println("Enter the group"
		    		+ "\n1.Relatives"
		    		+ "\n2.Personal Friends"
		    		+ "\n3.Professional Friends");
		            int choice1=sc.nextInt();
		            String group="";
		            if(choice1==1) group="Relative";
		            if(choice1==2) group="Personal Friends";
		            if(choice1==3) group="Professional Friends";
		            
		            rdao.getPerticularGroup(group);
		            break;
		    case 3: rdao.nameAndEmail();
		            break;
		    case 4:rdao.nameAndPhone();
		           break;
		    case 5:rdao.countByGroup();       
		   
		}
		 System.out.println("Do you want to continue? yes/no");
		 String input=sc.next();
		 if(input.equals("no"))
			 check=false;
	
		}while(check);
	}

}
