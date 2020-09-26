package com.capgemini.pecuniafinance.service;

import java.sql.Date;
import java.util.List;

import com.capgemini.pecuniafinance.model.Transactions;

public interface TransactionService {
	public double showBalance(long accountId);
	public List<Transactions> accountSummary(long accountId,Date startDate, Date endDate);
	
}
