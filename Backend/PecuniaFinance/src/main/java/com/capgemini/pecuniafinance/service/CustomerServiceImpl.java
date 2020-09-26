package com.capgemini.pecuniafinance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecuniafinance.dao.AccountDao;
import com.capgemini.pecuniafinance.dao.CustomerDao;
import com.capgemini.pecuniafinance.error.InvalidLoginCredentialsException;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerDao customerDao;

	@Autowired
	AccountDao accountDao;

	Account account;
	Customer user;

	static long accountNo = 987625361231l;

	@Override
	public Customer addUser(Customer customer, Account account) {
		logger.trace("Add User method accessed at service layer");
		account.setAccountId(accountNo);
		accountNo++;
		account.setAmount(0.0);
		customer.setAccount(account);
		account.setCustomer(customer);
		user = customerDao.save(customer);
		accountDao.save(account);
		return user;
	}

	@Override
	public Customer updateUser(Customer customer) {
		logger.trace("Update Customer details method accessed at service layer");
		Optional<Customer> findById = customerDao.findById(customer.getCustomerId());
		if (findById.isPresent()) {
			Customer updatedCustomer = customerDao.save(customer);
			return updatedCustomer;
		}
		return null;
	}

	public String removeUserById(long customer_id, long account_id) {
		logger.trace("Delete Customer account method accessed at service layer");
		Optional<Customer> findCustomer = customerDao.findById(customer_id);
		Optional<Account> findAccount = accountDao.findById(account_id);
		if (findCustomer.isPresent() && findAccount.isPresent()) {
			accountDao.deleteById(account_id);
			customerDao.deleteById(customer_id);
			return "User Removed";
		}
		return "User Not Found";

	}

	@Override
	public List<Customer> getAllCustomer() {
		logger.trace("Get all customers method accessed at service layer");
		return customerDao.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(long customerId) {
		logger.trace("Get customer by id method accessed at service layer");
		return customerDao.findById(customerId);
	}

	@Override
	public String userLogin(long customerId, String password) throws InvalidLoginCredentialsException {
		Customer user=customerDao.getOne(customerId);
		try {

			if(user==null) {
				logger.error("InvalidLoginCredentialsException thrown by the method");
				throw new InvalidLoginCredentialsException("User does not exist");
			}
			else
			{
				if(user.getCustomerId()==customerId && user.getPassword().equals(password)) {
					logger.info("Login Successful");
					return "Login Successful";
				}
				else {
					logger.error("InvalidLoginCredentialsException thrown by the method");
					throw new InvalidLoginCredentialsException("Invalid Customer Id or Password");
				}
			}


		}catch(Exception ex) {
			logger.error("InvalidLoginCredentialsException thrown by the method");
			throw new InvalidLoginCredentialsException("An error has occured!Please try again");
		}
	}

}
