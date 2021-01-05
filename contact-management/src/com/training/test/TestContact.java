package com.training.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.training.dao.ContactDao;
import com.training.model.Contact;

public class TestContact {
private Contact cont;
private ContactDao dao;
	
	@BeforeEach
	public void beforeTestMethod()
	{
		cont=new Contact();
		dao=new ContactDao();
	}
	@Test
	@DisplayName("to check if we are able to add the data")
	void testForAdd() {
		Contact contact=new Contact("Sam","Haveri",623288,"images/sam.jpeg",LocalDate.of(2000,3,20),"sam@yahoo.com","Relative");
	     int rowsAdded=dao.add(contact);
		assertEquals(1,rowsAdded);
	}
	@Test
	@DisplayName("to check if the delete operation is working")
	void testForDelete() {
		int mobile=216262;
		
	    int rowsDeleted=dao.remove(mobile);
		assertEquals(1,rowsDeleted);
	}
}
