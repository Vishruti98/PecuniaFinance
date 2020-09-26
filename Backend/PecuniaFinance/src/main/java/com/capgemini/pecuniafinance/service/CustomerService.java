package com.capgemini.pecuniafinance.service;


import java.util.List;
import java.util.Optional;

import com.capgemini.pecuniafinance.error.InvalidLoginCredentialsException;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;


public interface CustomerService {
	
	public Customer addUser(Customer customer, Account account);
	public Customer updateUser(Customer customer);
	public List<Customer> getAllCustomer();
	public Optional<Customer> getCustomerById(long customerId);
	public String removeUserById(long customer_id,long account_id);
	public String userLogin(long customerId,String password) throws InvalidLoginCredentialsException;
}
