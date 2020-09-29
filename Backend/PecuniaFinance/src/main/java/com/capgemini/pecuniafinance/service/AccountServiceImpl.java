package com.capgemini.pecuniafinance.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecuniafinance.dao.AccountDao;
import com.capgemini.pecuniafinance.dao.TransactionsDao;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Transactions;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	TransactionsDao transactionDao;
	
	@Override
	public String creditUsingSlip(Transactions transaction) {
		Account account=accountDao.getOne(transaction.getAccountId());
		if(account!=null)
		{
		account.setAmount(account.getAmount()+transaction.getTransactionAmount());
		accountDao.save(account);
		transactionDao.save(transaction);
		return "Amount Successfully Credited";
		}
		else
			return "Details Incorrect.Please try again";
	}

	@Override
	public String creditUsingCheque(Transactions transaction) {
		Account account=accountDao.getOne(transaction.getAccountId());
		if(account!=null)
		{
		account.setAmount(account.getAmount()+transaction.getTransactionAmount());
		accountDao.save(account);
		transactionDao.save(transaction);
		return "Amount Successfully Credited";
		}
		else
			return "Details Incorrect.Please try again";
	}

	@Override
	public String debitUsingSlip(Transactions transaction) {
		Account account=accountDao.getOne(transaction.getAccountId());
		if(account!=null && (account.getAmount()-transaction.getTransactionAmount())>=0)
		{
		account.setAmount(account.getAmount()-transaction.getTransactionAmount());
		accountDao.save(account);
		transactionDao.save(transaction);
		return "Amount Successfully Debited";
		}
		else
			return "Details Incorrect.Please try again";
	}

	@Override
	public String debitUsingCheque(Transactions transaction) {
		Account account=accountDao.getOne(transaction.getAccountId());
		if(account!=null && (account.getAmount()-transaction.getTransactionAmount())>=0)
		{
		account.setAmount(account.getAmount()-transaction.getTransactionAmount());
		accountDao.save(account);
		transactionDao.save(transaction);
		return "Amount Successfully Debited";
		}
		else
			return "Details Incorrect.Please try again";
	}
	
	

}
