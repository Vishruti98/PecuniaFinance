package com.capgemini.pecuniafinance.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Transactions;
import com.capgemini.pecuniafinance.dao.TransactionsDao;
@Service
public class TransactionServiceImpl implements TransactionService {
	
	Logger logger = LoggerFactory.getLogger(CustomerService.class);
	Account account= new Account();

	@Autowired
	private TransactionsDao tdao;
	
	@Override
	public double showBalance(long accountId) {
		logger.trace("Show balance method accessed at service layer");
		return tdao.getBalance(accountId);
	}
	
	@Override
	public List<Transactions> accountSummary(long accountId, Date startDate, Date endDate) {
		logger.trace("Account summary method accessed at service layer");
		return tdao.accountSummary(accountId, startDate, endDate);
	}
	
	//Implementation of Account validation method. 
	@Override
	public boolean accountValidation(long accountId) {
		 account=tdao.get(accountId);
		if(account==null) {
			return false;
		}
		else {
			return true;
		}
			
	}
}
