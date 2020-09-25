package com.capgemini.pecuniafinance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;
import com.capgemini.pecuniafinance.model.Loan;
import com.capgemini.pecuniafinance.service.CustomerService;
import com.capgemini.pecuniafinance.service.LoanService;

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
	
	@Test
	public void loanDisbursalTest() {
	
		LoanService loanService=mock(LoanService.class);
		Loan loan_a = new Loan();
		loan_a.setLoanId(4L);
		loan_a.setLoanAmount(50000.0);
		loan_a.setTenure(24);
		loan_a.setCreditScore(768);
		loan_a.setRateOfInterest(7.8);
		loan_a.setLoanStatus("Accepted");
		loan_a.setLoanType("Personal Loan");
		
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountType("Savings");
		account.setAmount(80000);
		account.setBranch("Delhi");
		
		Customer customer = new Customer();
		customer.setAadhaar(649282422559L);
		customer.setContact(9976564554L);
		customer.setCustomerId(2L);
		customer.setDob(new Date(1998,7,8));
		customer.setGender("Female");
		customer.setName("Akriti Goyal");
		customer.setPan("123CT34545");
		customer.setPassword("akriti");
		
		account.setCustomer(customer);
		customer.setAccount(account);
		
		loan_a.setAccount(account);
		
		when(loanService.loanDisbursal(loan_a)). thenReturn("Accepted");
		String status=loanService.loanDisbursal(loan_a);
		
		assertEquals("Accepted",status);
	}
	
	@Test
	public void addLoanRequestTest() {
		
		LoanService loanService=mock(LoanService.class);
		Loan loan_a = new Loan();
		loan_a.setLoanId(4001L);
		loan_a.setLoanAmount(50000.0);
		loan_a.setTenure(24);
		loan_a.setCreditScore(768);
		loan_a.setRateOfInterest(7.8);
		loan_a.setLoanStatus("Pending");
		loan_a.setLoanType("Personal Loan");
		
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountType("Savings");
		account.setAmount(80000);
		account.setBranch("Bangalore");
		
		Customer customer = new Customer();
		customer.setAadhaar(649282422559L);
		customer.setContact(9976564554L);
		customer.setCustomerId(2L);
		customer.setDob(new Date(1998,7,8));
		customer.setGender("Female");
		customer.setName("Debashish Goyal");
		customer.setPan("123CT34592");
		customer.setPassword("debashish");
		
		account.setCustomer(customer);
		customer.setAccount(account);
		
		loan_a.setAccount(account);
		
		when(loanService.addLoanRequest(loan_a)).thenReturn(loan_a);
		Loan requestedLoan=loanService.addLoanRequest(loan_a);
		assertEquals(4001,requestedLoan.getLoanId());
	}
}
