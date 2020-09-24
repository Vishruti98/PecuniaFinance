package com.capgemini.pecuniafinance.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.pecuniafinance.dao.AccountDao;
import com.capgemini.pecuniafinance.dao.CustomerDao;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired 
	AccountDao accountDao;
	
	Account account;
	Customer user;
	
	static long accountNo=987625361231l;
	
	@Override
	public Customer addUser(Customer customer,Account account) {
		//account=new Account();
		account.setAccountId(accountNo);
		accountNo++;
		account.setAmount(0.0);
		//account.setAccountType("Saving Account");
		//account.setBranch("EPIP Bangalore");
		customer.setAccount(account);
		account.setCustomer(customer);
		user=customerDao.save(customer);	
		return user;
	}

	@Override
	public Customer updateUser(Customer customer) {
		Customer user=customerDao.save(customer);
		return user;
	}

	
	
}
