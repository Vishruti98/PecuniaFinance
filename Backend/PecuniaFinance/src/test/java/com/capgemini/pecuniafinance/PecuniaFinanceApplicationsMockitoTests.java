package com.capgemini.pecuniafinance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;
import com.capgemini.pecuniafinance.service.CustomerService;

class PecuniaFinanceApplicationsMockitoTests {

	@Test
	public void testNoCustomerAdded() {
		CustomerService customerService=mock(CustomerService.class);
		when(customerService.addUser(null,null)).thenReturn(null);
		assertEquals(null, customerService.addUser(null,null));
	}
	
	@Test
	public void testAddCustomer() throws ParseException {
		CustomerService customerService=mock(CustomerService.class);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date dob=sdf.parse("12-01-1999");
		Customer custNew=new Customer(1610303l, "Vishruti","Vishruti@123", 8765376287l,809758294903l, "DWHJP30281",dob, "Female", null);
		Account accountNew=new Account();
		accountNew.setAccountType("Saving Account");
		accountNew.setBranch("EPIP Bangalore");
		Account account = null;
		Customer customer = null;
		when(customerService.addUser(customer, account)).thenReturn(custNew);
		Customer addedCustomer=customerService.addUser(customer,account);
		assertEquals(1610303l,addedCustomer.getCustomerId());
	}
	
	@Test
	public void testUpdateCustomer() throws ParseException {
		CustomerService customerService=mock(CustomerService.class);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date dob=sdf.parse("12-01-1999");
		Customer custNew=new Customer(1610303l, "Vishruti","Vishruti@123", 8765376287l,809758294903l, "DWHJP30281",dob, "Female", null);
		Account accountNew=new Account();
		accountNew.setAccountType("Saving Account");
		accountNew.setBranch("EPIP Bangalore");
		customerService.addUser(custNew, accountNew);
		
		Customer custNew1=new Customer(1610303l, "VMalviya","VMalviya@123", 8765376267l,809758294903l, "DWHJP30281",dob, "Female", null);
		Customer customer = null;
		when(customerService.updateUser(customer)).thenReturn(custNew1);
		Customer updateCustomer=customerService.updateUser(customer);
		assertEquals("VMalviya",updateCustomer.getName());
		assertEquals(1610303l,updateCustomer.getCustomerId());
	}
}
