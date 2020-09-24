package com.capgemini.pecuniafinance.service;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;

public interface CustomerService {
	
	public Customer addUser(Customer customer, Account account);
	public Customer updateUser(Customer customer);
}
